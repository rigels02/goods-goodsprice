
package goodsprice.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Raitis
 */
public class Good implements Serializable , Cloneable{
    
    private static final long serialVersionUID = 1L;
    
   public static final int FCOUNT = 5;
    private Date   cdate;
    private String name;
    private String shop;
    private double price;
    private double discount;
   

    public Good(){
    }
    public Good(Date cdate, String name, String shop, double price, double discount) {
        this.cdate = cdate;
        this.name = name;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }
    public Good makeCopy() throws CloneNotSupportedException{
     return (Good) this.clone();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.cdate);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.shop);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.discount) ^ (Double.doubleToLongBits(this.discount) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Good other = (Good) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.shop, other.shop)) {
            return false;
        }
        if (!Objects.equals(this.cdate, other.cdate)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "" + dformat() + ", name=" + name + ", shop=" + shop + ", price=" + price + ", discount=" + discount;
    }

   private String dformat(){
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");	
                
               return sdf.format(cdate);
               
   }
   
    public static Good getFromStream(DataInputStream din) throws IOException {
        
        Good good = new Good();
        //long tt = new Date().getTime();
        Date d = new Date();
        d.setTime(din.readLong());
        good.setCdate(d);
        good.setName(din.readUTF());
        good.setShop(din.readUTF());
        good.setPrice(din.readDouble());
        good.setDiscount(din.readDouble());
        return good;
    }
    
    public static void putToStream(DataOutputStream dos, Good good) throws IOException{
     dos.writeLong(good.getCdate().getTime());
     dos.writeUTF(good.getName());
     dos.writeUTF(good.getShop());
     dos.writeDouble(good.getPrice());
     dos.writeDouble(good.getDiscount());
    }
}
