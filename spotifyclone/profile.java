package spotifyclone;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class profile implements Serializable  {
    protected String name;
    protected String username;
    protected String profilepicturepath;
    private String email;
    private String password;
    ArrayList<profile> follower = new ArrayList<profile>();
    ArrayList<profile> following = new ArrayList<profile>();
  
    public profile(){

    }
    public profile(String n,String u,String path,String e,String p){
        name=n;
        username=u;
        profilepicturepath=path;
        email=e;
        password=p;
    }
    public profile(profile p){
        this.name=p.name;
        this.username=p.username;
        profilepicturepath=p.profilepicturepath;
        this.email=p.email;
        password=p.password;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getProfilepicturepath() {
        return profilepicturepath;
    }
    public void setProfilepicturepath(String profilepicturepath) {
        this.profilepicturepath = profilepicturepath;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<profile> getFollower() {
        return follower;
    }
    public void setFollower(ArrayList<profile> follower) {
        this.follower = follower;
    }
    public ArrayList<profile> getFollowing() {
        return following;
    }
    public void setFollowing(ArrayList<profile> following) {
        this.following = following;
    }
    public static boolean Registerprofile(String email,String password,String name, String usern) throws FileNotFoundException, IOException{
        if(email.endsWith(".com") && email.contains("@") && email.length()>15 &&email.length()<30 && isValidPassword(password) && password.length()>0 && password.length()<25 && isValidName(name) 
        && isValidUsername(usern)){
            profile temp=new profile(name, usern, null, email, password);
            writetofile(temp);
            return true;
        }
       return false;
    }
    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches() ;
    }
    public static boolean isValidName(String name) {
        String regex = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches() ;
    }
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*_).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean alreadyexists(String e) throws ClassNotFoundException, FileNotFoundException, IOException{
        
        ArrayList<profile> mylist= new ArrayList<profile>();
        mylist= profile.readfromfile();
        if(mylist.size()==0){return false;}
        for(int i=0;i<mylist.size();i++){
        

            if(e.equals(mylist.get(i).getEmail())){
                return true;
            }

        }
        return false;


    }
    public static profile authenticatProfile(String e,String p) throws ClassNotFoundException, FileNotFoundException, IOException{
        if(profile.alreadyexists(e)){
            ArrayList<profile> mylist=new ArrayList<profile>();
            mylist= profile.readfromfile();
            for(int i=0;i<mylist.size();i++){
                if(mylist.get(i).getEmail().equals(e)){
                    if(mylist.get(i).getPassword().equals(p)){
                        return mylist.get(i);
                    }
                    else{
                        return null;
                    }
                }
            }
            return null;
        }
        return null;
    }
    public static void writetofile(profile tempuser) throws FileNotFoundException, IOException{
        ObjectOutputStream oos;
        File f= new File("data/profiles.ser");
        if(f.exists()){
            oos= new MyObjectOutputStream(new FileOutputStream(f,true));
    
        }
        else{
            oos= new ObjectOutputStream(new FileOutputStream(f));
        }
        oos.writeObject(tempuser);
        oos.close();
    }

    public static ArrayList<profile> readfromfile() throws ClassNotFoundException, FileNotFoundException, IOException{
        ArrayList<profile> templist= new ArrayList<profile>();
        File f= new File("data/profiles.ser");
        ObjectInputStream ooi= new ObjectInputStream(new FileInputStream(f));
        try{
            while(true){
                profile pi= (profile)(ooi.readObject());
                templist.add(pi);
            }
            
        }
        catch(EOFException e){
                ooi.close();
                return templist;
        }
        catch(IOException e){
            System.out.print("ioexception in reading from file");
        }
        ooi.close();
        return templist;
        
    }
    

}
