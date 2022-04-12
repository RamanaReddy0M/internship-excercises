package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Example1 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println(
                transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                        .sorted(Comparator.comparingInt(Transaction::getValue))
                        .collect(Collectors.toList())
        );

        System.out.println(
                traders.stream().map(Trader::getCity)
                        .distinct().collect(Collectors.toList())
        );

       System.out.println(
                traders.stream().filter( trader -> trader.getCity().equalsIgnoreCase("Cambridge"))
                        .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList())
        );

        System.out.println(
                traders.stream().map(Trader::getName).sorted().reduce((a, b) -> a + " " + b)
        );

        System.out.println(traders.stream().anyMatch(t -> t.getCity().equals("Milan")));

        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .forEach(t -> System.out.print(t.getValue()+" "));

        System.out.println("\n"+
                transactions.stream().map(Transaction::getValue).reduce((x, y) -> x > y ? x : y)
        );

        System.out.println(
                transactions.stream().map(Transaction::getValue).reduce(Integer::min)
        );

    }
}

class Trader{

    private final String name;
    private final String city;

    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }

    public String getName(){
        return this.name;
    }

    public String getCity(){
        return this.city;
    }

    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}

class Transaction{

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader(){
        return this.trader;
    }

    public int getYear(){
        return this.year;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}
