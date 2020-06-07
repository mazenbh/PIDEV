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
import entity.Club;
import entity.EvenementParticipantUser;
import entity.Evenement_participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Haytham
 */
public class ServiceEvenementParticipant {
            public ArrayList<ServiceEvenementParticipant> particips;
            public ArrayList<Evenement_participant> participss;
            public ArrayList<Evenement_participant> participsss;
            public ArrayList<EvenementParticipantUser> participssss;

    public static ServiceEvenementParticipant instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvenementParticipant() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenementParticipant getInstance() {
        if (instance == null) {
            instance = new ServiceEvenementParticipant();
        }
        return instance;
    }   
    public boolean inscritEVENT(Evenement_participant c) {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/inscritmobile/?iduser="+authuser.user.getId()+"&idevenement="+c.getIdEvenement();
                req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        SmsSender s = new SmsSender();
        s.sendsms();
        return resultOK;
    }
   
         public ArrayList<Evenement_participant> parseEvents(String jsonText) {
        try {
            participss = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(eventsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Evenement_participant e = new Evenement_participant();
                 float iddd = Float.parseFloat(obj.get("id").toString());
                            e.setId((int) iddd);
                float id = Float.parseFloat(obj.get("idEvenement").toString());
                            e.setIdEvenement((int) id);
                              Club club  = new Club();
                   float idd = Float.parseFloat(obj.get("idUser").toString());
                            e.setIdUser((int) idd);

             
                participss.add(e);
            }

        } catch (IOException ex) {
                            System.out.println(ex);


        }
        return participss;
    }
                  public ArrayList<EvenementParticipantUser> parseEvents2(String jsonText) {
        try {
            participssss = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(eventsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                EvenementParticipantUser e = new EvenementParticipantUser();
                 
                float id = Float.parseFloat(obj.get("idEvenement").toString());
                            e.setIdEvenement((int) id);
                              Club club  = new Club();
                   float idd = Float.parseFloat(obj.get("idUser").toString());
                            e.setIdUser((int) idd);
                                            e.setUsername(obj.get("username").toString());
                                            e.setDesignation(obj.get("designation").toString());


             
                participssss.add(e);
            }

        } catch (IOException ex) {
                            System.out.println(ex);


        }
        return participssss;
    }

//          public ArrayList<EvenementParticipantUser> parseEvents(String jsonText) {
//        try {
//            participssss = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(eventsListJson);
//            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
//            System.out.println(list);
//            for (Map<String, Object> obj : list) {
//                EvenementParticipantUser e = new EvenementParticipantUser();
//                 float iddd = Float.parseFloat(obj.get("id").toString());
//                            e.setId((int) iddd);
//                float id = Float.parseFloat(obj.get("idEvenement").toString());
//                            e.setIdEvenement((int) id);
//                              Club club  = new Club();
//                   float idd = Float.parseFloat(obj.get("idUser").toString());
//                            e.setIdUser((int) idd);
//
//             
//                participssss.add(e);
//            }
//
//        } catch (IOException ex) {
//                            System.out.println(ex);
//
//
//        }
//        return participssss;
//    }
         
         
         
         
         
         
  public ArrayList<Evenement_participant> EVENTparticip(Evenement_participant c) {
 int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/partmobile/"+authuser.user.getId()+"/"+c.getIdEvenement();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participss = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return participss;
    }
//   public ArrayList<EvenementParticipantUser> EVENTparticip(EvenementParticipantUser c) {
// int e = Statics.current_user;
//        String url = Statics.BASE_URL + "/Event/partmobile/"+e+"/"+c.getIdEvenement();
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                participssss = parseEvents(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        
//        return participssss;
//    }
  
  
  
  
  
  
  
  
  
    public ArrayList<Evenement_participant> Annulerparticip(Evenement_participant c) {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/annuler/"+authuser.user.getId()+"/"+c.getIdEvenement();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participsss = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return participsss;
    }
       
         public ArrayList<EvenementParticipantUser> getAlLParticipants() {
             int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/participants/mobile/"+authuser.user.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               participssss = parseEvents2(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participssss;
    }
          public ArrayList<Evenement_participant> getAlLParticipantss() {
             int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/allparticipantmob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               participsss = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participsss;
    }
}
