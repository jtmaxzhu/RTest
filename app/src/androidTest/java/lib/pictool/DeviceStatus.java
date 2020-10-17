package lib.pictool;

/**
 * Created by Administrator on 2018/7/27.
 */

public enum DeviceStatus {

    FRY("炒", 0),
    DECOCT("煎", 1),
    STEW("炖", 2);


    private String name ;
    private int index ;

    private DeviceStatus( String name , int index ){
        this.name = name ;
        this.index = index ;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }


}
