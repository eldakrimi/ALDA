package first;
/*
 * Ellen Dahlgren, elda7225
 * Samarbetat med Adam Jacobsson, adja6724
 */

/*
 * Denna klass ska förberedas för att kunna användas som nyckel i en hashtabell.
 * Du får göra nödvändiga ändringar även i klasserna MyString och ISBN10.
 *
 * Hashkoden ska räknas ut på ett effektivt sätt och följa de regler och
 * rekommendationer som finns för hur en hashkod ska konstrueras. Notera i en
 * kommentar i koden hur du har tänkt när du konstruerat din hashkod.
 */
public class Book {
    private MyString title;
    private MyString author;
    private ISBN10 isbn;
    private MyString content;
    private int price;

    public Book(String title, String author, String isbn, String content, int price) {
        this.title = new MyString(title);
        this.author = new MyString(author);
        this.isbn = new ISBN10(isbn);
        this.content = new MyString(content);
    }

    public MyString getTitle() {
        return title;
    }

    public MyString getAuthor() {
        return author;
    }

    public ISBN10 getIsbn() {
        return isbn;
    }

    public MyString getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Book){
            return isbn.toString().equals(((Book) o).getIsbn().toString());
        }
        return false;
    }

    /*
       Då isbn-numret är unikt för varje bok valde vi att basera hashkoden på de unicodevärden som ingår i koden som sträng.
       Men då vi inte vet om ISBN-nummer är helt skilda från varandra eller om det finns en risk att vissa  bokstavsombinationer
       är mer lika än andra,vilket kan leda till clustring , multiplicera vi värdet på strängen med ett primtal. Dels för att få en större spridning men också för att undvika
       att hashCode%arraylängnd blir noll.
     */

    @Override
    public int hashCode(){

        int hashVal = 0;
        for(int i =0; i<isbn.toString().length();i++){
            hashVal+=37*isbn.toString().charAt(i);
        }

        return hashVal;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s Price: %d ISBN: %s lenght: %s", title, author, price, isbn, content.length());
    }

    private class MyString {

        private char[] data;

        public MyString(String title) {
            data = title.toCharArray();
        }

        public Object length() {
            return data.length;
        }

        @Override
        public String toString() {
            return new String(data);
        }

    }


    private class ISBN10 {

        private char[] isbn;

        public ISBN10(String isbn) {
            if (isbn.length() != 10)
                throw new IllegalArgumentException("Wrong length, must be 10");
            if (!checkDigit(isbn))
                throw new IllegalArgumentException("Not a valid isbn 10");
            this.isbn = isbn.toCharArray();
        }

        private boolean checkDigit(String isbn) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
            }
            int checkDigit = (11 - (sum % 11)) % 11;

            return isbn.endsWith(checkDigit == 10 ? "X" : "" + checkDigit);
        }


        @Override
        public String toString() {
            return new String(isbn);
        }
    }

}
