public class Info {
    String name,race,home,stat;
        public Info(String _name, String _race, String _home, String _stat){
                name = _name;
                race = _race;
                home = _home;
                stat = _stat;
        }

    public String getName(){
            return name;
    }
    public String getRace(){
            return race;
    }
    public String getHome(){
            return home;
    }
    public String getStat(){
            return stat;
    }

    public String createOutputString(){

        return String.join(":",name, race,home,stat);
    }


}
