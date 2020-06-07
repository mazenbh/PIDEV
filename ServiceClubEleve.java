/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import entity.ClubEleveuser;
import entity.Club_Eleve;
import entity.Club_Eleve2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Haytham
 */
public class ServiceClubEleve {
        public ArrayList<Club_Eleve> clubs;
                public ArrayList<Club_Eleve2> clubss;
        public ArrayList<ClubEleveuser> clubsss;

            public ArrayList<Club_Eleve> participss;
            public ArrayList<ClubEleveuser> participsss;

    public static ServiceClubEleve instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceClubEleve() {
        req = new ConnectionRequest();
    }

    public static ServiceClubEleve getInstance() {
        if (instance == null) {
            instance = new ServiceClubEleve();
        }
        return instance;
    }
         public ArrayList<Club_Eleve2> getAlLMemebers() {
             int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Club/members/mobile/"+authuser.user.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                clubss = parseClubMembers2(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubss;
    }
         public ArrayList<Club_Eleve2> parseClubMembers2(String jsonText) {
        try {
            clubss = new ArrayList<Club_Eleve2>();
            JSONParser j = new JSONParser();
            Map<String, Object> MemberclubsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(MemberclubsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) MemberclubsListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Club_Eleve2 c = new Club_Eleve2();
                 float idDD = Float.parseFloat(obj.get("id").toString());
                c.setId((int) idDD);
                float id = Float.parseFloat(obj.get("idClub").toString());
                c.setIdClub((int) id);
                 float id_user = Float.parseFloat(obj.get("idUser").toString());
                c.setId_User((int) id_user);
                c.setUsername(obj.get("username").toString());
                c.setEtat(obj.get("etat").toString());
                clubss.add(c);
                
                Map<String, Object> data2 = (Map<String, Object>) (obj.get("user"));

                    
            }

        } catch (IOException ex) {
                            System.out.println(ex);


        }
        return clubss;
    }
    public boolean modifEtat(Club_Eleve c) {
        String url = Statics.BASE_URL + "/Club/editmembremobile/" + c.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
          public boolean inscritclub(Club_Eleve c) {
              int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Club/inscritmobile/?iduser="+authuser.user.getId()+"&idclub="+c.getIdClub();
                req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
        return resultOK;
    }
    
      public ArrayList<Club_Eleve> CMember(Club_Eleve c) {
 int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Club/insmobile/"+authuser.user.getId()+"/"+c.getIdClub();    
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participss = parseClubMembers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return participss;
    }
//          public ArrayList<ClubEleveuser> CMember(ClubEleveuser c) {
// int e = Statics.current_user;
//        String url = Statics.BASE_URL + "/Club/insmobile/"+e+"/"+c.getIdClub();    
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                participsss = parseClubMembers(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        
//        return participsss;
//    }
      
//      public ArrayList<ClubEleveuser> parseClubMembers(String jsonText) {
//        try {
//            clubsss = new ArrayList<ClubEleveuser>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> MemberclubsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(MemberclubsListJson);
//            List<Map<String, Object>> list = (List<Map<String, Object>>) MemberclubsListJson.get("root");
//            System.out.println(list);
//            for (Map<String, Object> obj : list) {
//                ClubEleveuser c = new ClubEleveuser();
//                 float idDD = Float.parseFloat(obj.get("id").toString());
//                c.setId((int) idDD);
//                float id = Float.parseFloat(obj.get("idClub").toString());
//                c.setIdClub((int) id);
//                float idd = Float.parseFloat(obj.get("idUser").toString());
//                c.setId_User((int) idd);
//                c.setEtat(obj.get("etat").toString());
//                clubsss.add(c);
//                
//                Map<String, Object> data2 = (Map<String, Object>) (obj.get("user"));
//
//                    
//            }
//
//        } catch (IOException ex) {
//                            System.out.println(ex);
//
//
//        }
//        return clubsss;
//    }
      
      
      
      
      
    
    public ArrayList<Club_Eleve> parseClubMembers(String jsonText) {
        try {
            clubs = new ArrayList<Club_Eleve>();
            JSONParser j = new JSONParser();
            Map<String, Object> MemberclubsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(MemberclubsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) MemberclubsListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Club_Eleve c = new Club_Eleve();
                 float idDD = Float.parseFloat(obj.get("id").toString());
                c.setId((int) idDD);
                float id = Float.parseFloat(obj.get("idClub").toString());
                c.setIdClub((int) id);
                float idd = Float.parseFloat(obj.get("idUser").toString());
                c.setId_User((int) idd);
                c.setEtat(obj.get("etat").toString());
                clubs.add(c);
                
                Map<String, Object> data2 = (Map<String, Object>) (obj.get("user"));

                    
            }

        } catch (IOException ex) {
                            System.out.println(ex);


        }
        return clubs;
    }
    
}
