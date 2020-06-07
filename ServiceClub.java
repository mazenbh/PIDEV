/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entity.Club;
import entity.Club2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Haytham
 */
public class ServiceClub {

    public Club getOneById(int idClub) {
        Club s = new Club();
        Map<String, Object> m = getResponse("/Club/allmobby" + idClub);
        LinkedHashMap test = (LinkedHashMap) m.get("idclub");

        Double id = (Double) test.get("idclub");
        s.setIdClub(id.intValue());
        if (id.intValue() != 0) {
            id = (Double) test.get("idclub");
            s.setIdClub(id.intValue());

            return s;
        }
        return null;
    }
    public Map<String, Object> h = null;

    public ArrayList<Club> clubs;
    public ArrayList<Club> clubss;
    public ArrayList<Club2> clubsss;

    public static ServiceClub instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceClub() {
        req = new ConnectionRequest();
    }

    public static ServiceClub getInstance() {
        if (instance == null) {
            instance = new ServiceClub();
        }
        return instance;
    }

//    public boolean addTask(Task t) {
//        String url = Statics.BASE_URL + "/Club/clubs" ;
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
    public ArrayList<Club> parseClubs(String jsonText) {
        try {
            clubs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> clubsListtJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(clubsListtJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) clubsListtJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Club c = new Club();
                float id = Float.parseFloat(obj.get("idClub").toString());
                c.setIdClub((int) id);
                c.setNomClub(obj.get("nomClub").toString());
                  c.setDateCreation(obj.get("dateCreation").toString());
//                String s = obj.get("datecreation").toString();
//                int position = s.indexOf("timestamp");
//                int pos1 = s.length();
//                String sousChaine = s.substring(position + 10, pos1 - 1);
//                double d = Double.parseDouble(sousChaine);
//                long batch_date = (long) d;
//                Date dt = new Date(batch_date * 1000);
//                SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
//                String g = sfd.format(dt);
//                try {
//                    c.setDateCreation(sfd.parse(g));
//                } catch (ParseException ex) {
//                }

//                String h = obj.get("updated_at").toString();
//                int positionn = h.indexOf("timestamp");
//                int pos2 = h.length();
//                String sousChainee = h.substring(positionn + 10, pos2 - 1);
//                double d2 = Double.parseDouble(sousChainee);
//                long batch_datee = (long) d2;
//                Date dtt = new Date(batch_datee * 1000);
//                SimpleDateFormat sfdd = new SimpleDateFormat("yyyy-MM-dd");
//                String gg = sfdd.format(dtt);
//                try {
//                    c.setUpdated_at(sfdd.parse(gg));
//                } catch (ParseException ex) {
//                }
                c.setEmailClub(obj.get("emailClub").toString());

                c.setImageClub(obj.get("imageClub").toString());

                float idd = Float.parseFloat(obj.get("idPresident").toString());

                c.setIdPresident((int) idd);
                clubs.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex);

        }
        return clubs;
    }

    public ArrayList<Club> parseInfoClubs(String jsonText) {
        try {
            clubss = new ArrayList<>();
            JSONParser jj = new JSONParser();
            Map<String, Object> clubsListiJson = jj.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(clubsListiJson);
            List<Map<String, Object>> listt = (List<Map<String, Object>>) clubsListiJson.get("root");
            System.out.println(listt);
            for (Map<String, Object> obj : listt) {
                Club c = new Club();
                float id = Float.parseFloat(obj.get("idClub").toString());
                c.setIdClub((int) id);
                c.setNomClub(obj.get("nomClub").toString());
                c.setDateCreation(obj.get("dateCreation").toString());
//                String s = obj.get("datecreation").toString();
//                int position = s.indexOf("timestamp");
//                int pos1 = s.length();
//                String sousChaine = s.substring(position + 10, pos1 - 1);
//                double d = Double.parseDouble(sousChaine);
//                long batch_date = (long) d;
//                Date dt = new Date(batch_date * 1000);
//                SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
//                String g = sfd.format(dt);
//                try {
//                    c.setDateCreation(sfd.parse(g));
//                } catch (ParseException ex) {
//                }

//                String h = obj.get("updated_at").toString();
//                int positionn = h.indexOf("timestamp");
//                int pos2 = h.length();
//                String sousChainee = h.substring(positionn + 10, pos2 - 1);
//                double d2 = Double.parseDouble(sousChainee);
//                long batch_datee = (long) d2;
//                Date dtt = new Date(batch_datee * 1000);
//                SimpleDateFormat sfdd = new SimpleDateFormat("yyyy-MM-dd");
//                String gg = sfdd.format(dtt);
//                try {
//                    c.setUpdated_at(sfdd.parse(gg));
//                } catch (ParseException ex) {
//                }
                c.setEmailClub(obj.get("emailClub").toString());

                c.setImageClub(obj.get("imageClub").toString());

                float idd = Float.parseFloat(obj.get("idPresident").toString());

                c.setIdPresident((int) idd);

                clubss.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex);

        }
        return clubss;
    }

    
//     public ArrayList<Club2> parseInfoClubs(String jsonText) {
//        try {
//            clubsss = new ArrayList<>();
//            JSONParser jj = new JSONParser();
//            Map<String, Object> clubsListiJson = jj.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(clubsListiJson);
//            List<Map<String, Object>> listt = (List<Map<String, Object>>) clubsListiJson.get("root");
//            System.out.println(listt);
//            for (Map<String, Object> obj : listt) {
//                Club2 c = new Club2();
//                float id = Float.parseFloat(obj.get("idClub").toString());
//                c.setIdClub((int) id);
//                c.setNomClub(obj.get("nomClub").toString());
//                c.setDateCreation(obj.get("dateCreation").toString());
//
//                c.setEmailClub(obj.get("emailClub").toString());
//
//                c.setImageClub(obj.get("imageClub").toString());
//
////                float idd = Float.parseFloat(obj.get("idPresident").toString());
////
////                c.setIdPresident((int) idd);
//
//                clubsss.add(c);
//            }
//
//        } catch (IOException ex) {
//            System.out.println(ex);
//
//        }
//        return clubsss;
//    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Club> getAllTasks() {
        String url = Statics.BASE_URL + "/Club/allmobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                clubs = parseClubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubs;
    }

    public ArrayList<Club> getAllInfo() {
        int e = Statics.current_user;
        String url = Statics.BASE_URL + "/Club/infomob/"+authuser.user.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                clubss = parseInfoClubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubss;
    }
    
    
//      public ArrayList<Club2> getAllInfo() {
//        int e = Statics.current_user;
//        String url = Statics.BASE_URL + "/Club/infomob/"+e;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                clubsss = parseInfoClubs(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return clubsss;
//    }
    
    
    
    
    

    public ArrayList<Club> getAlLMemebers() {
        String url = Statics.BASE_URL + "/Club/members/mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                clubs = parseClubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubs;
    }

    public Map<String, Object> getResponse(String url) {
        url = "http://localhost/RISEWEBFINAL2/TalandWEB/web/app_dev.php" + url;
        System.out.println(url);
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(url);
        //r.setPost(false);

        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);

                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                h = p.parseJSON(targetReader);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h;
    }
}
