package connections;

public class JDBCDRIVER {
    private static JDBCDRIVER instance;

    public String className;
  private  JDBCDRIVER(String url){
        this.className=url;
    }
    public static JDBCDRIVER getClassName() {
        if(instance==null){
            instance=new JDBCDRIVER("com.mysql.cj.jdbc.Driver");
        }
        return instance;
    }
}
