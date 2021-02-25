package classes;

import java.util.ArrayList;
import java.util.List;

public class MyStructure<T> {
    private List<T> database =new ArrayList<>();
    private String  state;

    public MyStructure(String state) {
        this.state = state;
    }

    /**
     * add singe record to the data
     * @param data
     */
    public void addData(T data){
        database.add(data);
    }

    @Override
    public String toString() {
        return String.format("""
                *****%s*****
                %s
                """,state, database.toString());
    }
}
