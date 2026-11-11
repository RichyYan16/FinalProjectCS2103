package src.main.java;
import tech.tablesaw.api.Table;

public class DataCleaning {
    
    private Table data = new Table();

    public DataCleaning(Table data){
        this.data = data;
    }
    public void dataSummary(Table data){
        System.out.println(data.structure());
        System.out.println(data.summary());
    }

    public void dataCleaning(){
        
    }

}
