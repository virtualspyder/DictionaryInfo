package DictionaryInfo;

public class dictionaryInfo{

  private String definition;
  private String type;

  public dictionaryInfo(String d, String g){
    this.definition = d;
    this.type = g;
  }
  public String getDefinition(){
    return this.definition;
  }
  public String getType(){
    return this.type;
  }
}