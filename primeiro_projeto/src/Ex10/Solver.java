package Ex10;

import java.util.Scanner;

enum Coin {
    
    M10(0.10, 1),
    M25(0.25, 2),
    M50(0.50, 3),
    M100(1.00, 4);

    public double value;
    public int volume;
    private Coin(double value, int volume) {
        
        this.value = value;
        this.volume = volume;
    }
    
    public int getVolume() {
        return this.volume;
    }
    
    public String getDescription() {
        return "Coin: " + value;
    }
    
    public String toString() {
        return "Coin: " + value + " Volume: " + value;
    }
}
class Item {
    
    private String description;
    private int volume;
    
    public Item(String description, int volume){
        
        this.description = description;
        this.volume = volume;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setVolume(int volume){
        this.volume = volume;
    }
    
    public int getVolume(){
        return this.volume;
    }
    
  //  public String toString(){
        
   // }
}
class Pig{
    private String itens = "";
    private double value = 0;
    private int volume = 0;
    private int volumeMax;
    private boolean broken = false;
    //inicializa o volumeMax
    public Pig(int volumeMax){
        this.volumeMax = volumeMax;
        //se nao estiver quebrado e couber, adicione o value e o volume
    }
    
    public boolean addCoin(Coin moeda){
        //se não estiver quebrado e couber, adicione no volume e na descrição   
        if(isBroken()) {
            return false;
        }
        else{
            if(moeda.volume <= this.volumeMax - this.volume){
                this.volume += moeda.volume;
                this.value += moeda.value;
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    public boolean addItem(Item item){
        if(isBroken()){
            return false;
        }
        else{
            if(item.getVolume() <= (this.volumeMax - this.volume)){
                if(this.itens.length() < 1){
                    this.itens += item.getDescription();
                }
                else{
                    this.itens += ", " + item.getDescription();
                }
                this.volume += item.getVolume();
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    public boolean breakPig(){
        //se estiver quebrado, pegue e retorne o value
        if(!this.broken){
            this.broken = true;
            return true;
        } 
        return false;
    }
    
    public double getCoins(){
        //se estiver quebrado, pegue e retorno os itens
        if(isBroken()){
            return this.value;
        } 
        else{
             System.out.println("You must break the pig first");
        }
        return 0;
    }
    
    public String getItens(){
        if(isBroken()){
            return this.itens;
        }
        else{
            return ("You must break the pig first\n");
        }
    }
    public int getVolume(){
        return this.volume;
    }
    public int getVolumeMax(){
        return this.volumeMax;
    }
    public boolean isBroken(){
        //mostre o conteúdo do pig
        return this.broken;
    }
    
    public String toString(){
        return String.format("I:(%s) M:%.1f V:%d/%d EQ:%b", this.itens,
                            this.value,this.volume,this.volumeMax,this.broken);
    }
}

class Solver{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pig pig = new Pig(0);

        while(true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            System.out.println("$" + line);

            if(ui[0].equals("init")) {
                pig = new Pig( Integer.parseInt( ui[1]) );
            } else if(ui[0].equals("end")) {
                break;
            } else if(ui[0].equals("addCoin")) {
                if( ui[1].equals("10") ){
                    pig.addCoin(Coin.M10);
                }else if( ui[1].equals("25") ){
                    pig.addCoin(Coin.M25);             
                }else if( ui[1].equals("50") ){
                    pig.addCoin(Coin.M50);             
                }else if( ui[1].equals("100") ){
                    pig.addCoin(Coin.M100);             
                } 
            } else if(ui[0].equals("addItem")) {
                pig.addItem(new Item(ui[1], Integer.parseInt(ui[2])));
            } else if(ui[0].equals("getItens")) {
                System.out.println( pig.getItens() );
            } else if (ui[0].equals("getCoins")) {//km
                System.out.println( pig.getCoins() );
            } else if (ui[0].equals("show")) {//gas
                System.out.println(pig);
            } else if (ui[0].equals("breakPig")) {//gas
                pig.breakPig();
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
