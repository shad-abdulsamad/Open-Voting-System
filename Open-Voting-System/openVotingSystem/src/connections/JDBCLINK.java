package connections;

public final class JDBCLINK {
    
    private static JDBCLINK instance;
    public String finalUrl;
  private  JDBCLINK(String url){
        this.finalUrl=url;
    }
    public static JDBCLINK getUrl() {
        if(instance==null){
            instance=new JDBCLINK("jdbc:mysql://localhost/openvotingsystem");
        }
        return instance;
    }
}
