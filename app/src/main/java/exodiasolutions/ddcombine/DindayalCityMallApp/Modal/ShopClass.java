package exodiasolutions.ddcombine.DindayalCityMallApp.Modal;


public class ShopClass {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;


    String name;

    public ShopClass(String name, String icon,String id) {
        this.name = name;
        this.id=id;

        this.icon = icon;
    }

    String desc;



    String icon;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
