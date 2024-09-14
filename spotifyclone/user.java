package spotifyclone;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class user extends profile implements Serializable {
    ArrayList<Song> likedsongs= new ArrayList<Song>();
    ArrayList<Song> recentlyplayed= new ArrayList<Song>(4);
    ArrayList<album> playlists= new ArrayList<album>();
    ArrayList<profile> followers= new ArrayList<profile>();
    ArrayList<profile> following= new ArrayList<profile>();
    user(){ }
    public user(ArrayList<Song> likedsongs, ArrayList<Song> recentlyplayed, ArrayList<profile> followers,
            ArrayList<profile> following) {
        this.likedsongs = likedsongs;
        this.recentlyplayed = recentlyplayed;
        this.followers = followers;
        this.following = following;
    }
    
    public user(profile p){
        super(p);
    }
    public user(String e,String p,String n, String u, String path, ArrayList<Song> likedsongs,
            ArrayList<profile> followers, ArrayList<profile> following) {
        super(n,u,path, e,p );
        this.likedsongs = likedsongs;
        // this.recentlyplayed = recentlyplayed;
        this.followers = followers;
        this.following = following;

    }
    public void addtorecentlyplayed(Song temp){
            ArrayList<Song> myrecets= new ArrayList<Song>();
            myrecets= this.getRecentlyplayed();
            for(int i=0;i<myrecets.size();i++){
                if(myrecets.get(i).getName().equals(temp.getName())){
                    return ;
                }
            }
        
        this.recentlyplayed.add(temp);
    }
    public Song hasliked(String n){
        for(int i=0;i<likedsongs.size();i++){
            if(likedsongs.get(i).getName().equals(n)){
                return likedsongs.get(i);
            }
        }
        return null;

    }
    public static user getuserbyname(String n) throws ClassNotFoundException, FileNotFoundException, IOException{
        ArrayList<user> mylist= new ArrayList<>();
        mylist=user.readfromuserfile();
        // System.out.println(n);
        for(int i=0;i<mylist.size();i++){
            if(mylist.get(i).getName().equals(n)){
                return mylist.get(i);
            }
        }
        return null;
    }
    public album getplaylistbyname(String n){
        for(int i=0;i<this.getplaylists().size();i++){
            if(this.getplaylists().get(i).getAlbumname().equals(n)){
                return this.getplaylists().get(i);
            }
        }
        return null;

    }
    public ArrayList<Song> getLikedsongs() {
        return likedsongs;
    }
    public void setLikedsongs(ArrayList<Song> likedsongs) {
        this.likedsongs = likedsongs;
    }
    public ArrayList<Song> getRecentlyplayed() {
        ArrayList<Song> temp = new ArrayList<Song>(4);
        for(int i=this.recentlyplayed.size()-1,j=0;i>=0 && j<4;j++,i--){
            temp.add(this.recentlyplayed.get(i));
        
        }
        return temp;
    }
    public void setRecentlyplayed(ArrayList<Song> recentlyplayed) {
        this.recentlyplayed = recentlyplayed;
    }
    public ArrayList<profile> getFollowers() {
        return followers;
    }
    public void setFollowers(ArrayList<profile> followers) {
        this.followers = followers;
    }
    public ArrayList<profile> getFollowing() {
        return following;
    }
    public void setFollowing(ArrayList<profile> following) {
        this.following = following;
    }
    
    public static void writetofile(user tempuser) throws FileNotFoundException, IOException{
        ObjectOutputStream oos;
        File f= new File("data/users.ser");
        if(f.exists()){
            oos= new MyObjectOutputStream(new FileOutputStream(f,true));
    
        }
        else{
            oos= new ObjectOutputStream(new FileOutputStream(f));
        }
        oos.writeObject(tempuser);
        oos.close();
    }
    public void addplaylist(album a){
        playlists.add(a);

    }
    public void removeplaylist(album a){
        playlists.remove(a);
    }
    public static ArrayList<user> readfromuserfile() throws ClassNotFoundException, FileNotFoundException, IOException{
        ArrayList<user> templist= new ArrayList<user>();
        ObjectInputStream ooi= new ObjectInputStream(new FileInputStream(new File("data/users.ser")));
        try{
            while(true){
                user pi= (user)ooi.readObject();
                templist.add(pi);
            }
            
        }
        catch(EOFException e){
                return templist;
        }
        catch(IOException e){
            System.out.print("ioexception in reading from file in user class");
        }
        return templist;
        
    }
    public static void writealltofile(ArrayList<user> mylist) throws FileNotFoundException, IOException{
        ObjectOutputStream oos;
        File f= new File("data/users.ser");
        oos= new ObjectOutputStream(new FileOutputStream(f));
        try{
            for(int i=0;i<mylist.size();i++){
                oos.writeObject(mylist.get(i));

            }
        }
        catch(IOException e){System.out.println("ioexception in writing the list to the file");
    }
        oos.close();
    }
    public static void updateuser(user tempuser) throws ClassNotFoundException, FileNotFoundException, IOException{
        ArrayList<user> templist= new ArrayList<user>();
        
        templist= user.readfromuserfile();
        System.out.println("inside update uer");
        for(int i=0;i<templist.size();i++){
            if(templist.get(i).getName().equals(tempuser.getName())){
                templist.add(i,tempuser);
                break;
            }
        }
        user.writealltofile(templist);
        
    }
    public void removesongbyname(String n){
        for(int i=0;i<likedsongs.size();i++){
            if(likedsongs.get(i).getName().equals(n)){
                likedsongs.remove(i);
                break;
            }
        }
    }
    public ArrayList<album> getplaylists(){
        return playlists;
    }

}
