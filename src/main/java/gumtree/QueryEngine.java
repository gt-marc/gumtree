package gumtree;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Optional;

public interface QueryEngine {

  public long countMales();
  public Optional<AddressEntry> oldest(); // In an ideal world this should return a List
                                          // given that more than one person can have the
                                          // same birth date
  public Optional<AddressEntry> search(String name);

  // I'm using this get instance method as a poor man's
  // dependency injection manager
  public static QueryEngine getInstance(List<AddressEntry> entries) {
    return new QueryEngineImpl(entries);
  }

}

class QueryEngineImpl implements QueryEngine {

  private final List<AddressEntry> entries;

  public QueryEngineImpl(List<AddressEntry> entries) {
    this.entries = new ArrayList<AddressEntry>(entries); // I clone it here to avoid touching the original
    Collections.sort(this.entries, new Comparator<AddressEntry>() {
      @Override
      public int compare(AddressEntry lhs, AddressEntry rhs) {
          if (lhs.getDateOfBirth().getTime() < rhs.getDateOfBirth().getTime())
              return -1;
          else if (lhs.getDateOfBirth().getTime() == rhs.getDateOfBirth().getTime())
              return 0;
          else
              return 1;
      }
    });
  }

  @Override
  public long countMales() {
    return
        entries
            .stream()
            .filter(c -> c.isMale())
            .count();
  }

  @Override
  public Optional<AddressEntry> oldest() {
    if(entries.size() <= 0) return Optional.empty();

    return Optional.of(entries.get(0));
  }

  @Override
  public Optional<AddressEntry> search(String name){
    return
        entries
            .stream()
            .filter(c -> c.getName().startsWith(name))
            .findFirst();
  }
}


