package gumtree;

import com.opencsv.*;
import com.opencsv.bean.*;
import java.util.List;
import java.io.Reader;
import java.nio.file.Paths;
import java.nio.file.Files;
import static java.lang.System.out;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {
        AddressBookRepository repo = AddressBookRepository.getInstance();
        List<AddressEntry> entries = repo.load("AddressBook.csv");
        QueryEngine engine = QueryEngine.getInstance(entries);

        out.println("How many males are there in the AddressBook?");
        out.println(engine.countMales());
        out.println();

        out.println("Who is the oldest person in the AddressBook?");
        Optional<AddressEntry> response =
          engine.oldest();
        out.println(response.isPresent() ? response.get() : "The AddressBook is empty");
        out.println();

        out.println("What's the age difference between Paul and Bill, in days?");
        Optional<AddressEntry> billOpt = engine.search("Bill");
        Optional<AddressEntry> paulOpt = engine.search("Paul");

        if(billOpt.isPresent() && paulOpt.isPresent()) {
          AddressEntry bill = billOpt.get();
          AddressEntry paul = paulOpt.get();

          out.println(bill.daysDistance(paul));
        }
        else {
          out.println("Either Bill, Paul, or both couldn't be loaded from the AddressBook");
        }

    }

}
