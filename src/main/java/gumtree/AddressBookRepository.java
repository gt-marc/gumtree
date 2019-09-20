package gumtree;

import java.util.List;
import com.opencsv.*;
import com.opencsv.bean.*;

import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;

public interface AddressBookRepository {

  public List<AddressEntry> load(String resourcePath) throws Exception;

  // I'm using this get instance method as a poor man's
  // dependency injection manager
  public static AddressBookRepository getInstance() {
    return new AddressBookRepositoryImpl();
  }

}

class AddressBookRepositoryImpl implements AddressBookRepository {

    @Override
    public List<AddressEntry> load(String resourcePath) throws Exception {

        Class<AddressEntry> clazz = AddressEntry.class;

        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(clazz);

        InputStream is = Main.class.getResourceAsStream(resourcePath);

        Reader reader = new InputStreamReader(is);

        CsvToBean cb = new CsvToBeanBuilder(reader)
            .withType(clazz)
            .withMappingStrategy(ms)
            .build();

        return cb.parse();
    }

}
