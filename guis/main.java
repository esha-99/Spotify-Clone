package guis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import spotifyclone.Song;
import spotifyclone.album;
import spotifyclone.artist;
import spotifyclone.profile;
import spotifyclone.user;

public class main {
    public static void main(String args[]) throws IOException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException{
    //default profile and user:
    profile p = new profile("esha","esha1",null,"esha@gmail.com", "Hat123!" );
    profile.writetofile(p);
    artist onedirection = new artist("One Direction");
    artist JustinBieber = new artist("Justin Bieber"); 
    artist Halsey= new artist("Halsey");
    artist ed_shereen= new artist("Ed sheeran");
    artist powfu= new artist("Powfu");
    artist shawn_mendes= new artist("Shawn Mendes");
    Song nightchanges= new Song("Night Changes","songs\\nightchanges.wav","images\\nightchanges.jpg",16733,onedirection,"2014","4:01");
    Song Baby= new Song("Baby","songs\\baby.wav","images\\baby.jpeg",567,JustinBieber,"2015","3:57");
    Song withoutme= new Song("Without me","songs\\withoutme.wav","images\\withoutme.jpg",3788,Halsey,"2014","3:57");
    Song Perfect= new Song("Perfect","songs\\perfect.wav","images\\perfect.jpeg",4567,ed_shereen,"2012","4:24");
    Song Deathbed= new Song("Death Bed","songs\\deathbed.wav","images\\deathbed.jpg",5679,powfu,"2010","2:54");
    Song Stitches = new Song("Stitches","songs\\Stitches.wav","images\\stitches.jpg",6733,shawn_mendes,"2015","3:27");
    Song.addtoallsongs(nightchanges);
    Song.addtoallsongs(withoutme);
    Song.addtoallsongs(Baby);
    Song.addtoallsongs(Perfect);
    Song.addtoallsongs(Deathbed);
    Song.addtoallsongs(Stitches);
    user t= new user(p);
    ArrayList<Song> temp= new ArrayList<Song>();
    temp.add(nightchanges);
    temp.add(withoutme);
    temp.add(Baby);
    t.setLikedsongs(temp);
    ArrayList<Song> allbum1songs= new ArrayList<Song>();
    allbum1songs.add(Perfect);allbum1songs.add(withoutme);allbum1songs.add(Baby);
    ArrayList<Song> allbum2songs= new ArrayList<Song>();
    allbum2songs.add(nightchanges);allbum2songs.add(Deathbed);allbum2songs.add(Stitches); 
    album first= new album(3,allbum1songs,"Road Trip");
    album second= new album(3,allbum2songs,"Feel Good");
    t.addplaylist(first);
    t.addplaylist(second);
    t.addtorecentlyplayed(Baby);
    t.addtorecentlyplayed(Stitches);
    t.addtorecentlyplayed(nightchanges);
    t.addtorecentlyplayed(Deathbed);
    t.addtorecentlyplayed(Perfect);
    user.writetofile(t);
    //Call of landing page
    landingpage l= new landingpage();

    
   

}}

