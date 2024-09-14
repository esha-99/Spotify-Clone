package guis;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Arc2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

import guis.SignUp.MyActionListener;
import spotifyclone.Song;
import spotifyclone.album;
// import guis.Account.RoundedPanel.RoundedImagePanel;
import spotifyclone.profile;
import spotifyclone.user;

public class Account extends JFrame{
    JPanel p1,libraryPanel,options,names,songlist;
    static RoundedPanel p2;
    RoundedPanel p3;
    RoundedPanel p4;
    RoundedPanel homepanel;
    RoundedPanel searchpanel;
    RoundedPanel playlists;
    RoundedPanel likedsongs;
    RoundedPanel songone;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,songname,artistname,songimage;
    JScrollPane container;
    Clip clip;
    AudioInputStream sound;
    public Account(user curruser) throws IOException, UnsupportedAudioFileException, LineUnavailableException, ClassNotFoundException{
        user currentuser= user.getuserbyname(curruser.getName());
                
        System.out.println(currentuser);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
      
        
        setLayout(new GridBagLayout());
        setSize(1600,700);
        
        getContentPane().setBackground(new Color(16,16,16));
        
        GridBagConstraints c= new GridBagConstraints();
        c.fill= GridBagConstraints.HORIZONTAL;
        c.insets= new Insets(2, 8, 2,8);
        p1=new JPanel();
        p2=new RoundedPanel(30);
        
        p1.setBackground(new Color(16,16,16));
        p2.setBackground(new Color(27,27,27));
        p1.setPreferredSize(new Dimension(250,630));
        p2.setPreferredSize(new Dimension(970,630));
        c.gridy=0;
        c.gridx=0;
        p3 = new RoundedPanel(30);
        p3.setBackground( new Color(27,27,27,200));
        p3.setPreferredSize(new Dimension(250,200));
        p3.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        p4= new RoundedPanel(30);
        p4.setBackground( new Color(27,27,27));
        p4.setPreferredSize(new Dimension(250,420));

        //homepanel
        File f = new File("D:\\2nd semester\\OOP\\project\\images\\homeicon.png");
        BufferedImage bi= ImageIO.read(f);
        Image rbi=bi.getScaledInstance( 30,30, Image.SCALE_SMOOTH);
        ImageIcon homelogo = new ImageIcon(rbi);
        l1= new JLabel(homelogo);
        l2 = new JLabel("Home");
        l2.setFont(new Font("Gotham Circular",Font.PLAIN,20));
        l2.setForeground(Color.white);
        homepanel = new RoundedPanel(30);
        homepanel.setLayout(new GridLayout(1,3));
        // homepanel.add(new JLabel(" "));
        homepanel.add(l1);
        homepanel.add(l2);
        homepanel.add(new JLabel(" "));
        homepanel.setBackground(new Color(27,27,27,0));
        homepanel.setPreferredSize(new Dimension(250,60));
        homepanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
        homepanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try {
                    dispose();
                    Account ac= new Account(currentuser);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        p3.add(homepanel);
        searchpanel = new RoundedPanel(30);
        File f2 = new File("images\\searchicon.png");
        BufferedImage bi2=ImageIO.read(f2);
        Image rb2=bi2.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon searchicon= new ImageIcon(rb2);
        l3= new JLabel(searchicon);
        l4= new JLabel("Search");
        l4.setFont(new Font("Gotham Circular",Font.PLAIN,20));
        l4.setForeground(Color.white);
        searchpanel.setLayout(new GridLayout(1,3));
        searchpanel.add(l3);
        searchpanel.add(l4);
        searchpanel.add(new JLabel(" "));
        searchpanel.setBackground(new Color(27,27,27,0));
        searchpanel.setPreferredSize(new Dimension(250,60));
        searchpanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
        searchpanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                String Searchedsong=JOptionPane.showInputDialog("Search song");
                if(Song.getsongbyname(Searchedsong)==null){
                    JOptionPane.showMessageDialog(rootPane, "No such Song\nRemember your search is case sensistive");
                }
                else{
                    try {
                        addmp3player(currentuser, Song.getsongbyname(Searchedsong), null);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (UnsupportedAudioFileException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
        p3.add(searchpanel);
        l5 = new JLabel("Your Library");
        l5.setFont(new Font("Gotham Circular",Font.PLAIN,15));
        l5.setForeground(Color.white);
        File f3 = new File("images\\library.png");
        BufferedImage bi3= ImageIO.read(f3);
        Image rbi3=bi3.getScaledInstance( 27,27, Image.SCALE_SMOOTH);
        ImageIcon librarylogo = new ImageIcon(rbi3);
        l6 = new JLabel(librarylogo);
        libraryPanel= new JPanel();
        libraryPanel.setPreferredSize(new Dimension(250,60));
        libraryPanel.setBackground(new Color(27,28,28,0));
        libraryPanel.setLayout(new GridLayout(1,3));
        libraryPanel.add(l6);
        libraryPanel.add(l5);
        libraryPanel.add(new JLabel(" "));
        p4.add(libraryPanel);
        l7= new JLabel("Liked Songs");
        l8= new JLabel("Playlists");
        l7.setFont(new Font("Gotham Circular",Font.PLAIN,15));
        l7.setForeground(Color.white);
        l8.setFont(new Font("Gotham Circular",Font.PLAIN,15));
        l8.setForeground(Color.white);
        likedsongs= new RoundedPanel(26);
        likedsongs.add(l7);
        likedsongs.setBorder(BorderFactory.createEmptyBorder(1,2,1,2));
        likedsongs.setBackground(new Color(53, 57, 53,100));
        playlists= new RoundedPanel(26);
        playlists.add(l8);
        playlists.setBorder(BorderFactory.createEmptyBorder(1,2,1,2));
        playlists.setBackground(new Color(53, 57, 53,100));
        playlists.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try {
                    addplaylistcontainer(currentuser,currentuser.getplaylists());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        likedsongs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try {

                    p4.removeAll();
                    addcontainer(currentuser);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        options= new JPanel();
        options.add(likedsongs);
        options.add(playlists);
        options.setBackground(new Color(30,28,30,0));
        // p4.add(options);
        p1.add(p3);
        addcontainer(currentuser);
        p4.setBackground(new Color(27,27,27,200));
        p1.add(p4);
        add(p1,c);
        c.gridx=1;
        // openplaylist(currentuser, currentuser.getplaylists().get(0));
        addmainpage(user.getuserbyname(currentuser.getName()));

        add(p2,c); 


        
        setVisible(true);
    }
    public void addmainpage(user currentuser) throws IOException{
         p2.removeAll();
        // p2.add(new JLabel("esha"));
        
        JPanel header= new JPanel();
        header.setLayout(new GridLayout(1,11));
        JLabel Logout= new JLabel("Logout");
        File f4 = new File("images\\profileicon.png");
        BufferedImage bi4= ImageIO.read(f4);
        Image rbi4=bi4.getScaledInstance( 30,30, Image.SCALE_SMOOTH);
        ImageIcon profilelogo = new ImageIcon(rbi4);
        JLabel profile = new JLabel(profilelogo);
        Logout.setForeground(Color.white);
        Logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try{JOptionPane.showMessageDialog(rootPane, "Logging Out");
                    dispose();
                    landingpage lp= new landingpage();

                }catch(IOException e){

                }
            }
        });
       
        Logout.setFont(new Font("Gotham Circular",Font.PLAIN,23));
         //backbutton
       
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));


        header.add(Logout);
        header.add(profile);
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(970,40));
        p2.add(header);
        JLabel recentlyplayed=new JLabel("Recently Played");
        recentlyplayed.setFont(new Font("Gotham Circular",Font.PLAIN,15));
        recentlyplayed.setForeground(Color.white);recentlyplayed.setOpaque(false);
        JPanel t1= new JPanel();
        t1.setLayout(new FlowLayout(FlowLayout.LEFT));
        t1.setPreferredSize(new Dimension(900,30));
        t1.setOpaque(false);
        t1.add(recentlyplayed);
        p2.add(t1);
        addsongscards(currentuser, currentuser.getRecentlyplayed(), "creator",null);
        JLabel allsongs=new JLabel("All Songs");
        allsongs.setFont(new Font("Gotham Circular",Font.PLAIN,15));
        allsongs.setForeground(Color.white);recentlyplayed.setOpaque(false);
        JPanel t2= new JPanel();
        t2.setLayout(new FlowLayout(FlowLayout.LEFT));
        t2.setPreferredSize(new Dimension(900,30));
        t2.setOpaque(false);
        t2.add(allsongs);
        p2.add(t2);
        addsongscards(currentuser, Song.getallsongs(), "add",null);
        




    }
    public void addcontainer(user currentuser) throws IOException{
        //  songpanel

        p4.removeAll();
        // p4.revalidate();
        // p4.repaint();
        p4.add(options);
        songlist= new JPanel();
        songlist.setLayout(new GridLayout(40,1));
        songlist.setPreferredSize(new Dimension(140,3000));
        songlist.setBackground(new Color(27,28,28));
        for(int i=0;i<currentuser.getLikedsongs().size();i++){
        songname= new JLabel(currentuser.getLikedsongs().get(i).getName());
        artistname= new JLabel(currentuser.getLikedsongs().get(i).getCreator().getName());
        songname.setForeground(Color.white);
        songname.setFont(new Font("Gotham Circular",Font.PLAIN,18));
        artistname.setForeground(Color.lightGray);
        artistname.setFont(new Font("Gotham Circular",Font.PLAIN,14));
        songone= new RoundedPanel(30);
        
        names= new JPanel();
        names.setLayout(new GridLayout(2,1));
        names.setBackground(new Color(53, 57, 53,0));
        names.setPreferredSize(new Dimension(140,50));
        names.add(songname);
        names.add(artistname);
        
        
        File f4 = new File(currentuser.getLikedsongs().get(i).getSongcoverpagepath());
        BufferedImage temp=ImageIO.read(f4);
        BufferedImage bi4 = makeRoundedCorner(temp, 70);
        Image rb4=bi4.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon songImageIcon= new ImageIcon(rb4);
        songimage= new JLabel(songImageIcon);
        songone.setLayout(new FlowLayout(FlowLayout.CENTER));
        songone.add(songimage);
        songone.add(names);
        songone.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        songone.setBackground(new Color(27,28,28));
        songone.setPreferredSize(new Dimension(140,80));
        JLabel hidden = new JLabel(songname.getText());
        hidden.setVisible(false);
        songone.add(hidden);

        //songone eventlisteners
         songone.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                // System.out.println(hidden.getText());
                Song mytemp=null;
                for(int i=0;i<currentuser.getLikedsongs().size();i++){
                    if(hidden.getText().equals(currentuser.getLikedsongs().get(i).getName())){
                        mytemp=currentuser.getLikedsongs().get(i);
                        break;
                    }
                }
                try {
                    addmp3player(currentuser,mytemp,currentuser.getLikedsongs());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
         });    
        songlist.add(songone);
        }
       
        container= new JScrollPane();
        container.setBackground(new Color(27,28,28));
        container.setPreferredSize(new Dimension(250,300));
        container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        container.setViewportView(songlist);
        container.setBorder(BorderFactory.createEmptyBorder(5, 5,5,5));
        JScrollBar verticalScrollBar = container.getVerticalScrollBar();

        container.getViewport().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int adjustment = e.getUnitsToScroll() * 10; // Adjust the scrolling speed
                verticalScrollBar.setValue(verticalScrollBar.getValue() + adjustment);
            }
        });


        p4.add(container);
        // p4.setVisible(true);
    }
    public void addplaylistcontainer(user currentuser,ArrayList<album> playlists) throws IOException{
        // songpanel
        p4.removeAll();
        p4.add(options);
        System.out.println("inside add playlist container");
        songlist= new JPanel();
        songlist.setLayout(new GridLayout(40,1));
        songlist.setPreferredSize(new Dimension(140,3000));
        songlist.setBackground(new Color(27,28,28));
        for(int i=0;i<playlists.size();i++){
        songname= new JLabel(playlists.get(i).getAlbumname());
        artistname= new JLabel(playlists.get(i).getPlaytime());
        songname.setForeground(Color.white);
        songname.setFont(new Font("Gotham Circular",Font.PLAIN,18));
        artistname.setForeground(Color.lightGray);
        artistname.setFont(new Font("Gotham Circular",Font.PLAIN,14));
        songone= new RoundedPanel(30);
        
        names= new JPanel();
        names.setLayout(new GridLayout(2,1));
        names.setBackground(new Color(53, 57, 53,0));
        names.setPreferredSize(new Dimension(140,50));
        names.add(songname);
        names.add(artistname);
        
        p4.add(options);
        File f4;
        if(playlists.get(i).getAlbumsongs().size()==0){
            f4 = new File("images\\defaultcover.jpg");

        }
        else{
                f4 = new File(playlists.get(i).getAlbumsongs().get(0).getSongcoverpagepath());
        }
        BufferedImage temp=ImageIO.read(f4);
        BufferedImage bi4 = makeRoundedCorner(temp, 70);
        Image rb4=bi4.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon songImageIcon= new ImageIcon(rb4);
        songimage= new JLabel(songImageIcon);
        songone.setLayout(new FlowLayout(FlowLayout.CENTER));
        songone.add(songimage);
        songone.add(names);
        songone.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        songone.setBackground(new Color(27,28,28));
        songone.setPreferredSize(new Dimension(140,80));
        JLabel hidden = new JLabel(songname.getText());
        hidden.setVisible(false);
        songone.add(hidden);

        //songone eventlisteners
         songone.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                // System.out.println(hidden.getText());
                album mytemp=null;
                for(int i=0;i<currentuser.getplaylists().size();i++){
                    if(hidden.getText().equals(currentuser.getplaylists().get(i).getAlbumname())){
                        mytemp=currentuser.getplaylists().get(i);
                        break;
                    }
                }
                try {
                    // openplaylist(currentuser, mytemp);
                    openplaylist(user.getuserbyname(currentuser.getName()), user.getuserbyname(currentuser.getName()).getplaylistbyname(mytemp.getAlbumname()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
         });    
        songlist.add(songone);
        }
        JLabel addnew= new JLabel("+ Playlist");
        addnew.setFont(new Font("Gotham Circular",Font.PLAIN,17));
        addnew.setForeground(Color.white);
        RoundedPanel addnewplaylist= new RoundedPanel(4);
        addnewplaylist.setBorder(BorderFactory.createEmptyBorder(10,13, 10, 10));
        addnewplaylist.setLayout(new FlowLayout(FlowLayout.LEFT));
        addnewplaylist.setBackground(new Color(27,28,28));
        addnew.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                String nameofalbum= JOptionPane.showInputDialog("Enter the name of your new album");
                album newalbum= new album(nameofalbum);
                currentuser.addplaylist(newalbum);
                try {
                    addplaylistcontainer(currentuser, currentuser.getplaylists());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    user.updateuser(currentuser);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent me){
                addnew.setFont(new Font("Gotham Circular",Font.PLAIN,18));
            }
            public void mouseExited(MouseEvent me){
                addnew.setFont(new Font("Gotham Circular",Font.PLAIN,17));                
            }
        });
        addnewplaylist.add(addnew);
        songlist.add(addnewplaylist);
       
        container= new JScrollPane();
        container.setBackground(new Color(27,28,28));
        container.setPreferredSize(new Dimension(250,300));
        container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        container.setViewportView(songlist);
        container.setBorder(BorderFactory.createEmptyBorder(5, 5,5,5));
        JScrollBar verticalScrollBar = container.getVerticalScrollBar();

        container.getViewport().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int adjustment = e.getUnitsToScroll() * 10; // Adjust the scrolling speed
                verticalScrollBar.setValue(verticalScrollBar.getValue() + adjustment);
            }
        });
        p4.add(container);
        
    }
    public void addmp3player(user currentuser,Song currentsong,ArrayList<Song> songlist) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        System.out.println("inside addmp3player");
        
        p2.removeAll();
        // p2.add(new JLabel("esha"));
        
        JPanel header= new JPanel();
        header.setLayout(new GridLayout(1,11));
        JLabel Logout= new JLabel("Logout");
        File f4 = new File("images\\profileicon.png");
        BufferedImage bi4= ImageIO.read(f4);
        Image rbi4=bi4.getScaledInstance( 30,30, Image.SCALE_SMOOTH);
        ImageIcon profilelogo = new ImageIcon(rbi4);
        JLabel profile = new JLabel(profilelogo);
        Logout.setForeground(Color.white);
        Logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try{JOptionPane.showMessageDialog(rootPane, "Logging Out");
                    dispose();
                    landingpage lp= new landingpage();

                }catch(IOException e){

                }
            }
        });
       
        Logout.setFont(new Font("Gotham Circular",Font.PLAIN,23));
         //backbutton
        File f15 = new File("images\\backward.png");
        BufferedImage bi15= ImageIO.read(f15);
        Image rbi15=bi15.getScaledInstance( 30,30, Image.SCALE_SMOOTH);
        ImageIcon bbutton = new ImageIcon(rbi15);
        JLabel backbutton= new JLabel(bbutton);
        backbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                dispose();
                try {

                    try {
                        Account ac = new Account(currentuser);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        header.add(backbutton);
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));


        header.add(Logout);
        header.add(profile);
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(970,80));
        p2.add(header);
        JPanel mp3box = new JPanel();
        mp3box.setOpaque(false);
        mp3box.setLayout(new FlowLayout(FlowLayout.LEFT));
        mp3box.setPreferredSize(new Dimension(970,400));
        File f5 = new File(currentsong.getSongcoverpagepath());
        BufferedImage temp=ImageIO.read(f5);
        BufferedImage bi5= makeRoundedCorner(temp, 20);
        Image rb5=bi5.getScaledInstance(210,210,Image.SCALE_SMOOTH);
        ImageIcon songImageIcon= new ImageIcon(rb5);
        JLabel coverphoto= new JLabel(songImageIcon);
        
        mp3box.setBorder(BorderFactory.createEmptyBorder(100, 30, 80, 30));
        mp3box.setLayout(new FlowLayout(FlowLayout.LEFT));
        mp3box.add(coverphoto);
        JPanel frontbox= new JPanel();
        frontbox.setLayout(new FlowLayout(FlowLayout.LEFT));
        frontbox.setPreferredSize(new Dimension(400,200));
        frontbox.setOpaque(false);
        JLabel sname= new JLabel(currentsong.getName());
        sname.setFont(new Font("Gotham Circular",Font.BOLD,100));
        sname.setForeground(Color.white);
        frontbox.add(sname);
        JPanel info = new JPanel();

        JLabel name= new JLabel(currentsong.getCreator().getName() +" •");
        name.setFont(new Font("Gotham Circular",Font.PLAIN,14));
        name.setForeground(Color.white);
        JLabel year= new JLabel(currentsong.getUploadyear()+" •");
        year.setFont(new Font("Gotham Circular",Font.PLAIN,14));
        year.setForeground(Color.white);
        JLabel duration = new JLabel(currentsong.getDuration()+" •");
        duration.setFont(new Font("Gotham Circular",Font.PLAIN,14));
        duration.setForeground(Color.white);
        JLabel likes= new JLabel(currentsong.getLikes()+"  ");
        likes.setFont(new Font("Gotham Circular",Font.PLAIN,14));
        likes.setForeground(Color.white);
        info.add(name);info.add(year);info.add(duration);info.add(likes);
        info.setOpaque(false);
        frontbox.add(info);
        mp3box.add(frontbox);
        // mp3box.add(new JLabel("like "));
        p2.add(mp3box);

        //downbox
        RoundedPanel downbox = new RoundedPanel(50);
        downbox.setLayout(new FlowLayout(FlowLayout.LEFT));
        downbox.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));
        downbox.setBackground(new Color(27,27,27));

        File f6 = new File("images\\green heart.png");
        BufferedImage temp1=ImageIO.read(f6);
        Image rb6=temp1.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon hi= new ImageIcon(rb6);
        JLabel heart= new JLabel(hi);
        // currentuser;
        
        heart.addMouseListener(new MouseAdapter() {
            
            boolean liked= true;
           
            public void mouseClicked(MouseEvent me){
                if(currentuser.hasliked(sname.getText())==null){
                    liked=false;
                }
                if(liked==true){
                    JOptionPane.showMessageDialog(rootPane, "unliked:(");

                    liked=false;
                    
                    currentuser.removesongbyname(currentsong.getName());
                    
                    try {
                        user.updateuser(currentuser);
                        addcontainer(currentuser);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                else if(liked==false){
                    JOptionPane.showMessageDialog(rootPane,"liked:)");
                    liked=true;
                    currentuser.getLikedsongs().add(currentsong);
                    try {
                        user.updateuser(currentuser);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    try{addcontainer(currentuser);
                        user.updateuser(currentuser);
                    }catch(IOException | ClassNotFoundException e){ }
                }
            }
        });

        File f7 = new File("D:\\2nd semester\\OOP\\project\\images\\backward.png");
        BufferedImage temp7=ImageIO.read(f7);
        Image rb7=temp7.getScaledInstance(85,85,Image.SCALE_SMOOTH);
        ImageIcon hi7= new ImageIcon(rb7);
        JLabel backward= new JLabel(hi7);

        File f8 = new File("images\\playbutton.png");
        BufferedImage temp8=ImageIO.read(f8);
        Image rb8=temp8.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon hi8= new ImageIcon(rb8);
        JLabel playbutton= new JLabel(hi8);

        File f9 = new File("images\\forward.png");
        BufferedImage temp9=ImageIO.read(f9);
        Image rb9=temp9.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon hi9= new ImageIcon(rb9);
        JLabel forward= new JLabel(hi9);

        File f10 = new File("images\\volumedown.png");
        BufferedImage temp10=ImageIO.read(f10);
        Image rb10=temp10.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon hi10= new ImageIcon(rb10);
        JLabel volumedown= new JLabel(hi10);

        File f11 = new File("images\\volumeup.png");
        BufferedImage temp11=ImageIO.read(f11);
        Image rb11=temp11.getScaledInstance(37,37,Image.SCALE_SMOOTH);
        ImageIcon hi11= new ImageIcon(rb11);
        JLabel volumeup= new JLabel(hi11);

        File f12 = new File("images\\stop.png");
        BufferedImage temp12=ImageIO.read(f12);
        Image rb12=temp12.getScaledInstance(37,37,Image.SCALE_SMOOTH);
        ImageIcon hi12= new ImageIcon(rb12);
        JLabel stop= new JLabel(hi12);

        



        downbox.setPreferredSize(new Dimension(900,100));
        
        
        
        playbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try{
                File f= new File(currentsong.getSongpath());
                sound = AudioSystem.getAudioInputStream(f);
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.start();
                System.out.println("paly");
                currentuser.addtorecentlyplayed(Song.getsongbyname(sname.getText()));
                user.updateuser(currentuser);
                }catch(IOException e){

                } catch (UnsupportedAudioFileException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }}}
                );
        stop.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try {
                    sound.close();
                    clip.close();
                    clip.stop();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });
        volumedown.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainControl.getValue()-10.0f);

                File f= new File("songs\\baby.wav");
                
                try {
                    sound = AudioSystem.getAudioInputStream(f);
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }       }});
        forward.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try {
                    p2.removeAll();
                    if(songlist.indexOf(currentsong)+1>=songlist.size()){
                        addmp3player(currentuser,songlist.get(0), songlist);
                    }
                    else{
                        addmp3player(currentuser,songlist.get(songlist.indexOf(currentsong)+1), songlist);
                    }
                    
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        backward.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try{
                    p2.removeAll();
                    if(songlist.indexOf(currentsong)-1<0){
                        addmp3player(currentuser,songlist.get(songlist.size()-1), songlist);
                    }
                    else{
                        addmp3player(currentuser,songlist.get(songlist.indexOf(currentsong)-1), songlist);
                    }
                }
                catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){}
            }
        });
        


        // downbox.setOpaque(false);
        downbox.add(heart);
        downbox.add(new JLabel("                                          "));
        downbox.add(backward);downbox.add(new JLabel("        "));
        downbox.add(playbutton);downbox.add(new JLabel("            "));downbox.add(stop);downbox.add(new JLabel("         "));
        downbox.add(forward);downbox.add(new JLabel("                           "));
        downbox.add(volumedown);downbox.add(new JLabel("         "));
        downbox.add(volumeup);

        //adding events
        
        



        p2.add(downbox);

        

        

        
    }
    public void openplaylist(user currentuser,album playlist) throws IOException{
        p2.removeAll();
        JPanel header= new JPanel();
        header.setLayout(new GridLayout(1,8));
        JLabel Logout= new JLabel("Logout");
        File f4 = new File("images\\profileicon.png");
        BufferedImage bi4= ImageIO.read(f4);
        Image rbi4=bi4.getScaledInstance( 30,30, Image.SCALE_SMOOTH);
        ImageIcon profilelogo = new ImageIcon(rbi4);
        JLabel profile = new JLabel(profilelogo);
        Logout.setForeground(Color.white);
        Logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try{JOptionPane.showMessageDialog(rootPane, "Logging Out");
                    dispose();
                    landingpage lp= new landingpage();

                }catch(IOException e){

                }
            }
        });
       
        Logout.setFont(new Font("Gotham Circular",Font.PLAIN,23));
        //backbutton
        File f15 = new File("images\\backward.png");
        BufferedImage bi15= ImageIO.read(f15);
        Image rbi15=bi15.getScaledInstance( 30,30, Image.SCALE_SMOOTH);
        ImageIcon bbutton = new ImageIcon(rbi15);
        JLabel backbutton= new JLabel(bbutton);
        backbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                dispose();
                try {
                    Account ac= new Account(currentuser);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        header.add(backbutton);
        JLabel deleteplaylist= new JLabel("Delete ");
        deleteplaylist.setForeground(Color.white);
        deleteplaylist.setFont(new Font("Gotham Circular",Font.PLAIN,20));
        deleteplaylist.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                JOptionPane.showMessageDialog(rootPane,"Playlist deleted");
                for(int i=0;i<currentuser.getplaylists().size();i++){
                    if(currentuser.getplaylists().get(i).getAlbumname().equals(playlist.getAlbumname())){
                        currentuser.getplaylists().remove(i);
                        
                        try {
                            try {
                                user.updateuser(currentuser);
                            } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            Account ac = new Account(currentuser);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        header.add(deleteplaylist);
        
        
        // header.add(new JLabel(" "));
        header.add(new JLabel(" "));
        header.add(new JLabel("  "));
        header.add(new JLabel(" "));
        header.add(new JLabel(" "));

        header.add(Logout);
        header.add(profile);
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(970,80));
        p2.add(header);

        JPanel playlistinfo= new JPanel();
        playlistinfo.setLayout(new FlowLayout(FlowLayout.LEFT));
        playlistinfo.setPreferredSize(new Dimension(960,280));
        playlistinfo.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));

        //image
        File f5;
        if(playlist.getAlbumsongs().size()==0){
            f5= new File("images\\defaultcover.jpg");
        }
        else{
            f5 = new File(playlist.getAlbumsongs().get(0).getSongcoverpagepath());

        }
        BufferedImage temp=ImageIO.read(f5);
        BufferedImage bi5= makeRoundedCorner(temp, 20);
        Image rb5=bi5.getScaledInstance(210,210,Image.SCALE_SMOOTH);
        ImageIcon songImageIcon= new ImageIcon(rb5);
        JLabel coverphoto= new JLabel(songImageIcon);
        playlistinfo.add(coverphoto);
        //infobox
        
        JLabel playlistname= new JLabel(playlist.getAlbumname());
        playlistname.setFont(new Font("Gotham circular",Font.BOLD,80));
        playlistname.setForeground(Color.white);
        JLabel noofsongs= new JLabel(String.valueOf(playlist.getNoofsongs())+"  •");
        noofsongs.setFont(new Font("Gotham circular",Font.BOLD,20));
        noofsongs.setForeground(Color.white);
        noofsongs.setOpaque(false);
        JLabel playtime = new JLabel(playlist.getPlaytime());
        playtime.setFont(new Font("Gotham circular",Font.BOLD,20));
        playtime.setForeground(Color.white);
        playtime.setOpaque(false);
        JPanel infocontainer= new JPanel();
        infocontainer.setLayout(new GridLayout(2,1));
        infocontainer.setAlignmentX(LEFT_ALIGNMENT);
        JPanel t1= new JPanel();t1.setLayout(new FlowLayout(FlowLayout.LEFT));t1.add(playlistname);
        t1.setOpaque(false);
        infocontainer.add(t1);
        JPanel noandtime = new JPanel();
        noandtime.add(noofsongs);
        noandtime.add(playtime);
        noandtime.setOpaque(false);
        JPanel t2= new JPanel();t2.setLayout(new FlowLayout(FlowLayout.LEFT));t2.add(noandtime);
        infocontainer.setOpaque(false);
        t2.setOpaque(false);
        infocontainer.add(t2);
        
        playlistinfo.add(infocontainer);
        playlistinfo.setOpaque(false);
        p2.add(playlistinfo);
        
        addsongscards(currentuser,playlist.getAlbumsongs(),"delete",playlist.getAlbumname());  
    }
    public void addsongscards(user currentuser,ArrayList<Song> playlist,String option, String albumname) throws IOException{
        
        JPanel songcardholder= new JPanel();
        songcardholder.setLayout(new FlowLayout(FlowLayout.LEFT));
        songcardholder.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        songcardholder.setPreferredSize(new Dimension(2000,400));
        for(int k=0;k<playlist.size();k++){

        RoundedPanel songcard= new RoundedPanel(20);
        songcard.setPreferredSize(new Dimension(190,237));
        songcard.setLayout(new FlowLayout(FlowLayout.CENTER));
        songcard.setBorder(BorderFactory.createEmptyBorder(5,0, 5, 0));
        File f5 = new File(playlist.get(k).getSongcoverpagepath());
        BufferedImage temp=ImageIO.read(f5);
        BufferedImage bi5= makeRoundedCorner(temp, 18);
        Image rb5=bi5.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        ImageIcon songImageIcon= new ImageIcon(rb5);
        JLabel coverphoto= new JLabel(songImageIcon);
        songcard.add(coverphoto);
        JLabel songname= new JLabel(playlist.get(k).getName());
        songname.setFont(new Font("Gotham circular",Font.BOLD,20));
        songname.setForeground(Color.white);
        JLabel hidden= new JLabel(songname.getText());
        hidden.setVisible(false);
        songcard.add(hidden);
        JLabel optionbutton= new JLabel();
        optionbutton.setFont(new Font("Gotham circular",Font.BOLD,16));
        optionbutton.setForeground(Color.white);
        container= new JScrollPane();
        container.setPreferredSize(new Dimension(965,255));
        container.setBorder(BorderFactory.createEmptyBorder(5, 5,5,5));

        if(option=="delete"){
        optionbutton.setText("Delete");
        
        optionbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                // System.out.println("deleted");
                for(int i=0;i<currentuser.getplaylists().size();i++){
                    if(albumname.equals(currentuser.getplaylists().get(i).getAlbumname())){
                       
                        // System.out.println("found "+currentuser.getplaylists().get(i).getAlbumname());
                        for(int j=0;j<currentuser.getplaylists().get(i).getAlbumsongs().size();j++){
                            if(currentuser.getplaylists().get(i).getAlbumsongs().get(j).getName().equals(hidden.getText())){
                                // System.out.println("removing"+currentuser.getplaylists().get(i).getAlbumsongs().get(j).getName());
                                currentuser.getplaylists().get(i).getAlbumsongs().remove(j);
                                try{
                                    JOptionPane.showMessageDialog(rootPane, "Deleted");
                                    user.updateuser(currentuser);
                                    openplaylist(currentuser, currentuser.getplaylists().get(i));
                                }catch(ClassNotFoundException | IOException e){ }
                                break;
                            }}
                       
                    }
                }
            
            }
        });}
        else if(option=="add"){
            optionbutton.setText("Add");
            optionbutton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me){
                    int found=-1;
                    String input=JOptionPane.showInputDialog("Enter the playlist you want to add to");
                    for(int i=0;i<currentuser.getplaylists().size();i++){
                        if(currentuser.getplaylists().get(i).getAlbumname().equals(input)){
                            System.out.println("playlistfound ");
                            found=i;break;
                        }
                    }
                    if(found==-1){
                        JOptionPane.showMessageDialog(rootPane,"Enter a valid playlist\nYour input is case sensitive");
                    }
                    else{
                        boolean songexists= false;
                        for(int i=0;i<currentuser.getplaylists().get(found).getAlbumsongs().size();i++){
                            if(hidden.getText().equals(currentuser.getplaylists().get(found).getAlbumsongs().get(i).getName())){
                                JOptionPane.showMessageDialog(rootPane,"Already in this playlist");
                                songexists=true;
                                break;
                            }
                        }
                        if(songexists==false){
                            System.out.println("song doesnt exists");
                            Song tempsong= new Song();
                            for(int i=0;i<Song.getallsongs().size();i++){
                                if(Song.getallsongs().get(i).getName().equals(hidden.getText())){
                                    tempsong=Song.getallsongs().get(i);
                                    currentuser.getplaylists().get(found).getAlbumsongs().add(tempsong);
                                    try {
                                        user.updateuser(currentuser);
                                        addplaylistcontainer(currentuser, currentuser.getplaylists());
                                    } catch (ClassNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (FileNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    try {
                                        user.updateuser(currentuser);
                                    } catch (ClassNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (FileNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    System.out.println("inside if");
                                    JOptionPane.showMessageDialog(rootPane,"Song added");
                                }
                            }
                            
                        }
                    }
                }
            });
            
        }
        else if(option=="creator"){
            container.setBorder(BorderFactory.createEmptyBorder(0, 5,0,5));

            optionbutton.setText(playlist.get(k).getDuration()+"  "+playlist.get(k).getCreator().getName());
            optionbutton.setFont(new Font("Gotham Circular", Font.PLAIN, 14));
        }
        else if(option=="all songs"){
            container.setBorder(BorderFactory.createEmptyBorder(0, 5,0,5));
            optionbutton.setText(playlist.get(k).getDuration()+"  "+playlist.get(k).getLikes());
            optionbutton.setFont(new Font("Gotham Circular", Font.PLAIN, 14));
        }
        JPanel t3= new JPanel();
        t3.setLayout(new GridLayout(2,1));
        JPanel t4= new JPanel();t4.setLayout(new FlowLayout(FlowLayout.CENTER));t4.add(songname);t4.setOpaque(false);

        JPanel t5= new JPanel();t5.setLayout(new FlowLayout(FlowLayout.CENTER));t5.add(optionbutton);t5.setOpaque(false);

        t3.add(t4);
        t3.add(t5);
        t3.setOpaque(false);
        songcard.add(t3);
        //designing
        songcard.setBackground(new Color(44,44,44));
        // System.out.println("adding"+hidden.getText());
        songcard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                try{

                    addmp3player(currentuser, Song.getsongbyname(hidden.getText()), playlist);
                }catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){}
            }
        });
        songcardholder.add(songcard);}
        // System.out.println("this was the whole call");
        songcardholder.setBackground(p2.getBackground());
        // songcardholder.setOpaque(false);

        
        container.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        container.setViewportView(songcardholder);
        container.setOpaque(false);
        JScrollBar horizontalScrollBar = container.getHorizontalScrollBar();

        container.getViewport().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int adjustment = e.getUnitsToScroll() * 10; // Adjust the scrolling speed
                horizontalScrollBar.setValue(horizontalScrollBar.getValue() + adjustment);
            }
        });

        p2.add(container);

    }
    class RoundedPanel extends JPanel {
        private int cornerRadius;
    
        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
    
            // Set background color (change as needed)
            g2d.setColor(getBackground());
            g2d.fill(roundedRectangle);
    
            // Set border color (change as needed)
            g2d.setColor(getBackground());
            g2d.draw(roundedRectangle);
    
            g2d.dispose();
        }
        
}
public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = output.createGraphics();
    
    // This is what we want, but it only does hard-clipping, i.e. aliasing
    // g2.setClip(new RoundRectangle2D ...)
    // so instead fake soft-clipping by first drawing the desired clip shape
    // in fully opaque white with antialiasing enabled...
    g2.setComposite(AlphaComposite.Src);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
    
    // ... then compositing the image on top,
    // using the white shape from above as alpha source
    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(image, 0, 0, null);
    
    g2.dispose();
    
    return output;
}

    }

