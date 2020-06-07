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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.l10n.ParseException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import entity.Club;
import entity.Evenement;
import entity.Evenement2;
import utils.Statics;

/**
 *
 * @author Haytham
 */
public class ServiceEvenement {

    public ArrayList<Evenement> events;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addEvent(Evenement e) {
        // int ee = Statics.current_club;
        String url = Statics.BASE_URL + "/Event/addmobil/?designation=" + e.getDesignation() + "&nbplaces=" + e.getNbPlaces() + "&datedebut=" + e.getDateDebut() + "&datefin=" + e.getDateFin() + "&description=" + e.getDescription() + "&typeevenement=" + e.getTypeEvenement() + "&idclub=" + e.getIdClub();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        String accessToken = "EAAWbZCxehHWsBAIfnJPmyTflvGMbsZBotFQbpXPa2ThmwZBp9gcoWJCIfUdetJbLZCeAXOZCyO6tgZCaIBWFmZB0FEnaoDd0KNWOZAgNoJXuMBNTnLJ2S7XpKXfa2fAe82ZAVsxI48bZC9dDD3y1jc1OOdGOMaXZBZAHEXPn2yHAlWEGSxP8rFI68R7nzg2hque5u3kZC7QKiPTxnrPS6Ix2jd4fS";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        FacebookType response = fbClient.publish("me/feed", FacebookType.class, Parameter.with("voila une nouvelle Inscription via ", "haytham"), Parameter.with("link", "http://127.0.0.1:8000/" + e.getDesignation() + e.getDescription()));
        System.out.println("fb.com/" + response.getId());
        return resultOK;
    }

    public boolean addEvent2(Evenement2 e) {
        String url = Statics.BASE_URL + "/Event/addmobile/?designation=" + e.getDesignation() + "&nbplaces=" + e.getNbPlaces() + "&datedebut=" + e.getDateDebut() + "&datefin=" + e.getDateFin() + "&description=" + e.getDescription() + "&typeevenement=" + e.getTypeEvenement() + "&idclub=" + e.getIdClub();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        String accessToken = "EAAWbZCxehHWsBAMStXTbJGHwuaEWhZBHkcKHHXYCJ69NBOj04FX1kvrUotEWggIyXtux3fIO0iZChpRRsqSN6pktwZCoZA7JdT1m1K1FGqAsFnlm0GvPieVaPxb0IZCQftGZCDRHlzeXD94X6Lg6azWZApQcZAHz11ZCjs1sRAi7TInDyru1ZAkvLFTtSmZCspuxq8fsMZA21DvVPkVpgZCiXpgATp";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        FacebookType response = fbClient.publish("me/feed", FacebookType.class, Parameter.with("voila une nouvelle Inscription via ", "haytham"), Parameter.with("link", "http://127.0.0.1:8000/" + e.getDesignation() + e.getDescription()));
        System.out.println("fb.com/" + response.getId());
        return resultOK;
    }

    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(eventsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("idEvenement").toString());
                e.setIdEvenement((int) id);
                Club club = new Club();
                float idd = Float.parseFloat(obj.get("idClub").toString());
                e.setIdClub((int) idd);

                e.setDesignation(obj.get("designation").toString());

                float nb = Float.parseFloat(obj.get("nbPlaces").toString());

                e.setNbPlaces((int) nb);
                e.setEtat(obj.get("etat").toString());
                e.setDateDebut(obj.get("dateDebut").toString());
                e.setDateFin(obj.get("dateFin").toString());


                e.setDescription(obj.get("description").toString());
                e.setTypeEvenement(obj.get("typeEvenement").toString());


                events.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex);

        }
        return events;
    }

    public ArrayList<Evenement> getAllTasks() {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/allmobilee/" + authuser.user.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
     public ArrayList<Evenement> getAllTasks2() {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/allmobilee/" ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Evenement> stat() {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/statmobile/"+authuser.user.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Evenement> stat2() {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Event/statmobile2/"+authuser.user.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

 
}
