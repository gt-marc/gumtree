package gumtree;

import com.opencsv.bean.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class AddressEntry {

    @CsvBindByPosition(position = 0)
    private String name;
 
    @CsvBindByPosition(position = 1)
    private String gender;
 
    @CsvBindByPosition(position = 2)
    private String dateOfBirth;

    public String getName() {return name.trim();}
    public String getGender() {return gender.trim();}
    public String getDateOfBirthDesc() {return dateOfBirth.trim();}

    @Override
    public String toString() {
      return
        "Name:          " + getName()        + "\n" +
        "Gender:        " + getGender()      + "\n" +
        "Date of Birth: " + getDateOfBirthDesc();
    }

    public boolean isMale() {
      return getGender().toLowerCase().equals("male");
    }

    // I know the new Date/Time library in java is a lot better,
    // but I haven't had the opportunity to use it much, so I'll
    // go for safer grounds
    public Date getDateOfBirth() {
      try {
        return format.parse(getDateOfBirthDesc());
      }
      catch (ParseException e) {
        throw new RuntimeException("The Date of Birth is not in a correct format", e);
      }
    }

    public long daysDistance(AddressEntry that) {
      long diff = this.getDateOfBirth().getTime() - that.getDateOfBirth().getTime();
      return Math.abs(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    // TOOLS

    // I know SimpleDateFormat is bad with multiple threads, but
    // this application is not multithreaded
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

}

