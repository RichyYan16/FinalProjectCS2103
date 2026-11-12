package src.main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import tech.tablesaw.api.Table;

public class DataCleaning {    
    public static void main(String[] args) throws IOException {
        Path input = Path.of("src/main/data");
        Path output = Path.of("cleaned-data");

        for (String folder : new String[]{"stocks", "etfs"}){
            try (Stream<Path> files = Files.walk(input.resolve(folder))){
                files.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".csv"))
                .forEach(file -> {
                    try{
                        Table data = Table.read().csv(file.toFile());
                        data = dataCleaning(data);
                        Path destination = output.resolve(input.relativize(file));
                        Files.createDirectories(destination.getParent());
                        data.write().csv(destination.toFile());
                    } catch (Exception e){
                        System.err.println("Failed: " + file + " - " + e.getMessage());
                    }
                });
            }
        }
    }

    public static Table dataCleaning(Table data){
        data = data.dropRowsWithMissingValues();
        data = data.dropWhere(
            data.numberColumn("High").isLessThan(data.numberColumn("Low"))
            .or(data.numberColumn("Close").isLessThan(0))
            .or(data.numberColumn("Open").isLessThan(0))
        );
        data = data.dropDuplicateRows();
        return data;
    }
}
