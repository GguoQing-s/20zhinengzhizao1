package com.example.a20zhinengzhizao1;


import android.util.Log;


import com.example.a20zhinengzhizao1.bean1.Application;
import com.example.a20zhinengzhizao1.bean1.Automobile;

import com.example.a20zhinengzhizao1.bean1.FbzpA2;
import com.example.a20zhinengzhizao1.bean1.Gyslb;
import com.example.a20zhinengzhizao1.bean1.Jbxxsql;
import com.example.a20zhinengzhizao1.bean1.Ktdga;
import com.example.a20zhinengzhizao1.bean1.MaterialA1;
import com.example.a20zhinengzhizao1.bean1.Order1;
import com.example.a20zhinengzhizao1.bean1.Repair1;
import com.example.a20zhinengzhizao1.bean1.Scx;
import com.example.a20zhinengzhizao1.bean1.Shipment1;
import com.example.a20zhinengzhizao1.bean1.Tjyl;
import com.example.a20zhinengzhizao1.bean1.Transaction1;
import com.example.a20zhinengzhizao1.bean1.User2;
import com.example.a20zhinengzhizao1.bean1.VehicleA2;
import com.example.a20zhinengzhizao1.bean1.Warehousing;
import com.example.a20zhinengzhizao1.bean_z.TZ_SQL;
import com.example.a20zhinengzhizao1.bean_z.YLYZ;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fi.iki.elonen.NanoHTTPD;

public class S_MyService extends NanoHTTPD {
    public static final int PORT = 3333;
    private AppClient appClient;

    public S_MyService(int port, AppClient appClient) {
        super(3333);
        this.appClient = appClient;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> map = new HashMap<>();
        Map<String, String> zMap = new HashMap<>();
        JSONObject bodyJson;
        String body;
        Map<String, String> file;
        JSONObject allJson = new JSONObject();
        try {
            allJson.put("RESULT", "S");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            switch (uri) {
                case "/get_yl_yz":
                    List<YLYZ> ylyzs = LitePal.findAll(YLYZ.class);
                    JSONArray jsonArray555 = new JSONArray();
                    for (int i = 0; i < ylyzs.size(); i++) {
                        YLYZ ylyz = ylyzs.get(i);
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("id", i + 1);
                        jsonObject1.put("name", ylyz.getName());
                        jsonObject1.put("num", ylyz.getNum());
                        jsonObject1.put("path", ylyz.getPath());
                        jsonObject1.put("yz", ylyz.getNumber());
                        jsonArray555.put(jsonObject1);
                    }
                    JSONObject jsonObject51 = new JSONObject();
                    jsonObject51.put("ROWS_DETAIL", jsonArray555);
                    jsonObject51.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject51.toString());
                case "/set_yl_yz":
                    session.parseBody(zMap);
                    body = zMap.get("postData");
                    bodyJson = new JSONObject(body);
                    YLYZ ylyz = new YLYZ();
                    ylyz.setNumber(bodyJson.optInt("yz"));
                    ylyz.updateAll("name=?",bodyJson.optString("ylmc"));
                    return newFixedLengthResponse(Response.Status.OK,"application/json","{\"RESULT\": \"S\"}");



                case "/update_gyslb":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Gyslb gyslb1 = new Gyslb();
                    gyslb1.setGysbh(bodyJson.getString("gysbh"));
                    gyslb1.setMc(bodyJson.getString("mc"));
                    gyslb1.setCs(bodyJson.getString("cs"));
                    gyslb1.setDd(bodyJson.getString("dd"));
                    gyslb1.setFr(bodyJson.getString("fr"));
                    gyslb1.setLxr(bodyJson.getString("lxr"));
                    gyslb1.setTel(bodyJson.getString("tel"));
                    gyslb1.setYwfw(bodyJson.getString("ywfw"));
                    gyslb1.setImage(bodyJson.getString("image"));
                    gyslb1.updateAll("gysbh=? and mc=?", bodyJson.getString("gysbh"), bodyJson.getString("mc"));
                    JSONObject jsonObject50 = new JSONObject();
                    jsonObject50.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject50.toString());


                case "/update_tjyl":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Tjyl tjyl1 = new Tjyl();
                    tjyl1.setGysbh(bodyJson.getString("gysbh"));
                    tjyl1.setYlmc(bodyJson.getString("ylmc"));
                    tjyl1.setYlbh(bodyJson.getString("ylbh"));
                    tjyl1.setJg(bodyJson.getString("jg"));
                    tjyl1.setPath(bodyJson.getString("path"));
                    tjyl1.updateAll("gysbh=? and ylbh=?", bodyJson.getString("gysbh"), bodyJson.getString("ylbh"));
                    JSONObject jsonObject49 = new JSONObject();
                    jsonObject49.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject49.toString());

                case "/delete_tjyl":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    LitePal.deleteAll(Tjyl.class, "gysbh=? and ylbh=?", bodyJson.getString("bh"), bodyJson.getString("ylbh"));
                    LitePal.deleteAll(YLYZ.class, "name=? and num=?", bodyJson.getString("ylbh"),bodyJson.getString("bh"));
                    JSONObject jsonObject48 = new JSONObject();
                    jsonObject48.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject48.toString());

                case "/delete_gylls":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    LitePal.deleteAll(Gyslb.class, "gysbh=? and mc=?", bodyJson.getString("bh"), bodyJson.getString("name"));
                    JSONObject jsonObject47 = new JSONObject();
                    jsonObject47.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject47.toString());
                case "/get_gyslb":
                    List<Gyslb> gyslbs = LitePal.findAll(Gyslb.class);
                    JSONArray jsonArray22 = new JSONArray();
                    for (int i = 0; i < gyslbs.size(); i++) {
                        Gyslb gyslb = gyslbs.get(i);
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("gysbh", gyslb.getGysbh());
                        jsonObject3.put("mc", gyslb.getMc());
                        jsonObject3.put("cs", gyslb.getCs());
                        jsonObject3.put("dd", gyslb.getDd());
                        jsonObject3.put("fr", gyslb.getFr());
                        jsonObject3.put("lxr", gyslb.getLxr());
                        jsonObject3.put("tel", gyslb.getTel());
                        jsonObject3.put("ywfw", gyslb.getYwfw());
                        jsonObject3.put("image", gyslb.getImage());

                        jsonArray22.put(jsonObject3);
                    }
                    JSONObject jsonObject46 = new JSONObject();
                    jsonObject46.put("ROWS_DETAIL", jsonArray22);
                    jsonObject46.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject46.toString());


                case "/set_gyslb":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Gyslb gyslb = new Gyslb();
                    gyslb.setGysbh(bodyJson.getString("gysbh"));
                    gyslb.setMc(bodyJson.getString("mc"));
                    gyslb.setCs(bodyJson.getString("cs"));
                    gyslb.setDd(bodyJson.getString("dd"));
                    gyslb.setFr(bodyJson.getString("fr"));
                    gyslb.setLxr(bodyJson.getString("lxr"));
                    gyslb.setTel(bodyJson.getString("tel"));
                    gyslb.setYwfw(bodyJson.getString("ywfw"));
                    gyslb.setImage(bodyJson.getString("image"));
                    gyslb.save();
                    JSONObject jsonObject45 = new JSONObject();
                    jsonObject45.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject45.toString());


                case "/get_tjyl":
                    List<Tjyl> tjyls = LitePal.findAll(Tjyl.class);
                    JSONArray jsonArray21 = new JSONArray();
                    for (int i = 0; i < tjyls.size(); i++) {
                        Tjyl tjyl = tjyls.get(i);
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("gssbh", tjyl.getGysbh());
                        jsonObject3.put("ylmc", tjyl.getYlmc());
                        jsonObject3.put("ylbh", tjyl.getYlbh());
                        jsonObject3.put("jg", tjyl.getJg());
                        jsonObject3.put("path", tjyl.getPath());
                        jsonArray21.put(jsonObject3);
                    }
                    JSONObject jsonObject44 = new JSONObject();
                    jsonObject44.put("ROWS_DETAIL", jsonArray21);
                    jsonObject44.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject44.toString());
                case "/set_tjyl":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Tjyl tjyl = new Tjyl();

                    tjyl.setGysbh(bodyJson.getString("gysbh"));
                    tjyl.setYlmc(bodyJson.getString("ylmc"));
                    tjyl.setYlbh(bodyJson.getString("ylbh"));
                    tjyl.setJg(bodyJson.getString("jg"));
                    tjyl.setPath(bodyJson.getString("path"));
                    tjyl.save();
                    YLYZ ylyz2 = new YLYZ(bodyJson.getString("ylmc"),bodyJson.getString("gysbh"), bodyJson.getString("path")
                            , 100);
                    ylyz2.save();
                    JSONObject jsonObject43 = new JSONObject();
                    jsonObject43.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject43.toString());


                case "/update_factory_fbzp":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    FbzpA2 fbzp1 = new FbzpA2();
                    fbzp1.setShr(bodyJson.getString("shr"));
                    fbzp1.setShsj(bodyJson.getString("shsj"));
                    fbzp1.setZt(bodyJson.getString("zt"));
                    fbzp1.updateAll("bh=? and naem=?", bodyJson.getString("bh"), bodyJson.getString("name"));
                    JSONObject jsonObject42 = new JSONObject();
                    jsonObject42.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject42.toString());
                case "/get_factory_fbzp":
                    List<FbzpA2> fbzps = LitePal.findAll(FbzpA2.class);
                    JSONArray jsonArray3 = new JSONArray();
                    for (int i = 0; i < fbzps.size(); i++) {
                        FbzpA2 fbzp = fbzps.get(i);
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("bh", fbzp.getBh());
                        jsonObject3.put("zt", fbzp.getZt());
                        jsonObject3.put("naem", fbzp.getNaem());
                        jsonObject3.put("xl", fbzp.getXl());
                        jsonObject3.put("hy", fbzp.getHy());
                        jsonObject3.put("szd", fbzp.getSzd());
                        jsonObject3.put("gzdz", fbzp.getGzdz());
                        jsonObject3.put("tel", fbzp.getTel());
                        jsonObject3.put("email", fbzp.getEmail());
                        jsonObject3.put("gw", fbzp.getGw());
                        jsonObject3.put("xz", fbzp.getXz());
                        jsonObject3.put("zyyq", fbzp.getZyyq());
                        jsonObject3.put("gzjlyq", fbzp.getGzjlyq());
                        jsonObject3.put("gwyq", fbzp.getGwyq());
                        jsonObject3.put("shr", fbzp.getShr());
                        jsonObject3.put("shsj", fbzp.getShsj());
                        jsonObject3.put("time", fbzp.getTime());
                        jsonArray3.put(jsonObject3);
                    }
                    JSONObject jsonObject6 = new JSONObject();
                    jsonObject6.put("ROWS_DETAIL", jsonArray3);
                    jsonObject6.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject6.toString());


                case "/set_factory_fbzp":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    FbzpA2 fbzp = new FbzpA2();
                    fbzp.setBh(bodyJson.getString("bh"));
                    fbzp.setZt(bodyJson.getString("zt"));
                    fbzp.setNaem(bodyJson.getString("name"));
                    fbzp.setXl(bodyJson.getString("xl"));
                    fbzp.setHy(bodyJson.getString("hy"));
                    fbzp.setSzd(bodyJson.getString("szd"));
                    fbzp.setGzdz(bodyJson.getString("gzdz"));
                    fbzp.setTel(bodyJson.getString("tel"));
                    fbzp.setEmail(bodyJson.getString("email"));
                    fbzp.setGw(bodyJson.getString("gw"));
                    fbzp.setXz(bodyJson.getString("xz"));
                    fbzp.setZyyq(bodyJson.getString("zyyq"));
                    fbzp.setGzjlyq(bodyJson.getString("gzjlyq"));
                    fbzp.setGwyq(bodyJson.getString("gwyq"));
                    fbzp.setShr(bodyJson.getString("shr"));
                    fbzp.setShsj(bodyJson.getString("shsj"));
                    fbzp.setTime(bodyJson.getString("time"));

                    fbzp.save();
                    JSONObject jsonObject4 = new JSONObject();
                    jsonObject4.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject4.toString());


                case "/get_order":
                    List<Order1> dingdans = LitePal.findAll(Order1.class);
                    JSONArray jsonArray11 = new JSONArray();
                    for (int i = 0; i < dingdans.size(); i++) {
                        Order1 dingdan = dingdans.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name", dingdan.getName());
                        jsonObject.put("jine", dingdan.getJine());
                        jsonObject.put("time", dingdan.getTime());
                        jsonArray11.put(jsonObject);
                    }
                    JSONObject jsonObject23 = new JSONObject();
                    jsonObject23.put("ROWS_DETAIL", jsonArray11);
                    jsonObject23.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject23.toString());
                case "/set_order":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Order1 dingdan = new Order1();
                    dingdan.setName(bodyJson.getString("name"));
                    dingdan.setJine(bodyJson.getString("jine"));
                    dingdan.setTime(bodyJson.getString("time"));
                    dingdan.save();
                    JSONObject jsonObject22 = new JSONObject();
                    jsonObject22.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject22.toString());

                case "/update_vehicle":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    List<VehicleA2> vehicleAS1 = LitePal.findAll(VehicleA2.class);
                    VehicleA2 vehicleA1 = new VehicleA2();
                    for (int i = 0; i < vehicleAS1.size(); i++) {
                        VehicleA2 vehicleA = vehicleAS1.get(i);
                        if (vehicleA.getName().equals(bodyJson.getString("name")) && vehicleA.getClxh().equals(bodyJson.getString("clxh"))) {
                            vehicleA1.setSl(Integer.parseInt(vehicleA.getSl()) - Integer.parseInt(bodyJson.getString("sl")) + "");
                        }
                    }

                    vehicleA1.updateAll("name=? and clxh=?", bodyJson.getString("name"), bodyJson.getString("clxh"));
                    JSONObject jsonObject21 = new JSONObject();
                    jsonObject21.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject21.toString());
                case "/get_vehiclea":
                    List<VehicleA2> vehicleAS = LitePal.findAll(VehicleA2.class);
                    JSONArray jsonArray17 = new JSONArray();
                    for (int i = 0; i < vehicleAS.size(); i++) {
                        VehicleA2 vehicleA = vehicleAS.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name", vehicleA.getName());
                        jsonObject.put("clxh", vehicleA.getClxh());
                        jsonObject.put("jb", vehicleA.getJb());
                        jsonObject.put("cs", vehicleA.getCs());
                        jsonObject.put("lx", vehicleA.getLx());
                        jsonObject.put("hbbz", vehicleA.getHbbz());
                        jsonObject.put("sssj", vehicleA.getSssj());
                        jsonObject.put("jg", vehicleA.getJg());
                        jsonObject.put("sl", vehicleA.getSl());
                        jsonObject.put("sms", vehicleA.getSms());
                        jsonObject.put("cspz", vehicleA.getCspz());
                        jsonObject.put("video", vehicleA.getVideo());
                        jsonObject.put("image", vehicleA.getImage());
                        jsonArray17.put(jsonObject);
                    }
                    JSONObject jsonObject40 = new JSONObject();
                    jsonObject40.put("ROWS_DETAIL", jsonArray17);
                    jsonObject40.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject40.toString());

                case "/set_vehiclea":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    VehicleA2 vehicleA = new VehicleA2();
                    vehicleA.setName(bodyJson.getString("name"));
                    vehicleA.setClxh(bodyJson.getString("clxh"));
                    vehicleA.setJb(bodyJson.getString("jb"));
                    vehicleA.setCs(bodyJson.getString("cs"));
                    vehicleA.setLx(bodyJson.getString("lx"));
                    vehicleA.setHbbz(bodyJson.getString("hbbz"));
                    vehicleA.setSssj(bodyJson.getString("sssj"));
                    vehicleA.setJg(bodyJson.getString("jg"));
                    vehicleA.setSl(bodyJson.getString("sl"));
                    vehicleA.setVideo(bodyJson.getString("video"));
                    vehicleA.setImage(bodyJson.getString("image"));
                    vehicleA.setSms(bodyJson.getString("sms"));
                    vehicleA.setCspz(bodyJson.getString("pz"));
                    vehicleA.save();
                    JSONObject jsonObject39 = new JSONObject();
                    jsonObject39.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject39.toString());
                case "/get_automobile":
                    List<Automobile> automobiles = LitePal.findAll(Automobile.class);
                    JSONArray jsonArray16 = new JSONArray();
                    for (int i = 0; i < automobiles.size(); i++) {
                        Automobile automobile = automobiles.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("cjh", automobile.getCjh());
                        jsonObject.put("scxh", automobile.getScxh());
                        jsonObject.put("name", automobile.getName());
                        jsonObject.put("xh", automobile.getXh());
                        jsonObject.put("sl", automobile.getSl());
                        jsonArray16.put(jsonObject);
                    }
                    JSONObject jsonObject38 = new JSONObject();
                    jsonObject38.put("ROWS_DETAIL", jsonArray16);
                    jsonObject38.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject38.toString());
                case "/set_automobile":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Automobile automobile = new Automobile();
                    automobile.setCjh(bodyJson.getString("cjh"));
                    automobile.setScxh(bodyJson.getString("scxh"));
                    automobile.setName(bodyJson.getString("name"));
                    automobile.setXh(bodyJson.getString("xh"));
                    automobile.setSl(bodyJson.getString("sl"));
                    automobile.save();
                    JSONObject jsonObject37 = new JSONObject();
                    jsonObject37.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject37.toString());
                case "/update_repair":

                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Repair1 repair1 = new Repair1();
                    repair1.setZt(bodyJson.getString("zt"));
                    repair1.setWxsj(bodyJson.getString("wxsj"));
                    repair1.updateAll("clbh=? and clxh=?", bodyJson.getString("clbh"), bodyJson.getString("clxh"));
                    JSONObject jsonObject36 = new JSONObject();
                    jsonObject36.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject36.toString());
                case "/get_repair":
                    List<Repair1> repairs = LitePal.findAll(Repair1.class);
                    JSONArray jsonArray15 = new JSONArray();
                    for (int i = 0; i < repairs.size(); i++) {
                        Repair1 repair = repairs.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("wxsj", repair.getWxsj());
                        jsonObject.put("zt", repair.getZt());
                        jsonObject.put("clbh", repair.getClbh());
                        jsonObject.put("clxh", repair.getClxh());
                        jsonObject.put("clwt", repair.getClwt());
                        jsonObject.put("bxsj", repair.getBxsj());
                        jsonArray15.put(jsonObject);
                    }
                    JSONObject jsonObject35 = new JSONObject();
                    jsonObject35.put("ROWS_DETAIL", jsonArray15);
                    jsonObject35.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject35.toString());

                case "/set_repair":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Repair1 repair = new Repair1();
                    repair.setWxsj(bodyJson.getString("wxsj"));
                    repair.setZt(bodyJson.getString("zt"));
                    repair.setClbh(bodyJson.getString("clbh"));
                    repair.setClxh(bodyJson.getString("clxh"));
                    repair.setClwt(bodyJson.getString("clwt"));
                    repair.setBxsj(bodyJson.getString("bxsj"));

                    repair.save();
                    JSONObject jsonObject34 = new JSONObject();
                    jsonObject34.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject34.toString());
                case "/get_scx":
                    List<Scx> scxes = LitePal.findAll(Scx.class);
                    JSONArray jsonArray14 = new JSONArray();
                    for (int i = 0; i < scxes.size(); i++) {
                        Scx scx = scxes.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("cjm", scx.getCjm());
                        jsonObject.put("scxm", scx.getScxm());
                        jsonObject.put("zt", scx.getZt());
                        jsonObject.put("hj", scx.getHj());
                        jsonObject.put("ts", scx.getTs());
                        jsonArray14.put(jsonObject);
                    }
                    JSONObject jsonObject33 = new JSONObject();
                    jsonObject33.put("ROWS_DETAIL", jsonArray14);
                    jsonObject33.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject33.toString());

                case "/set_scx":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Scx scx = new Scx();
                    scx.setCjm(bodyJson.getString("bh"));
                    scx.setScxm(bodyJson.getString("scxm"));
                    scx.setZt(bodyJson.getString("zt"));
                    scx.setHj(bodyJson.getString("hj"));
                    scx.setTs(bodyJson.getString("ts"));
                    scx.save();
                    JSONObject jsonObject32 = new JSONObject();
                    jsonObject32.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject32.toString());

                case "/update_ms":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Ktdga ktdg3 = new Ktdga();
                    ktdg3.setMs(bodyJson.getString("ms"));
                    ktdg3.updateAll("bianhao=?", bodyJson.getString("bianhao"));
                    JSONObject jsonObject31 = new JSONObject();
                    jsonObject31.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject31.toString());
                case "/update_wd":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Ktdga ktdg2 = new Ktdga();
                    ktdg2.setDushu(bodyJson.getString("wd"));
                    ktdg2.updateAll("bianhao=?", bodyJson.getString("bianhao"));
                    JSONObject jsonObject30 = new JSONObject();
                    jsonObject30.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject30.toString());
                case "/update_ktkg":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Ktdga ktdg1 = new Ktdga();
                    if (bodyJson.get("pd").equals("灯光")) {
                        ktdg1.setDg(bodyJson.getString("zt"));
                    }
                    if (bodyJson.get("pd").equals("空调")) {
                        ktdg1.setKt(bodyJson.getString("zt"));
                    }
                    ktdg1.updateAll("bianhao=?", bodyJson.getString("bianhao"));
                    JSONObject jsonObject29 = new JSONObject();
                    jsonObject29.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject29.toString());
                case "/get_ktkg":
                    List<Ktdga> ktdgs = LitePal.findAll(Ktdga.class);
                    JSONArray jsonArray13 = new JSONArray();
                    for (int i = 0; i < ktdgs.size(); i++) {
                        Ktdga ktdg = ktdgs.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("bh", ktdg.getBianhao());
                        jsonObject.put("dg", ktdg.getDg());
                        jsonObject.put("kt", ktdg.getKt());
                        jsonObject.put("ms", ktdg.getMs());
                        jsonObject.put("ds", ktdg.getDushu());
                        jsonArray13.put(jsonObject);
                    }
                    JSONObject jsonObject28 = new JSONObject();
                    jsonObject28.put("ROWS_DETAIL", jsonArray13);
                    jsonObject28.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject28.toString());
                case "/set_ktkg":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Ktdga ktdg = new Ktdga();
                    ktdg.setBianhao(bodyJson.getString("bh"));
                    ktdg.setDg(bodyJson.getString("dg"));
                    ktdg.setKt(bodyJson.getString("kt"));
                    ktdg.setMs(bodyJson.getString("ms"));
                    ktdg.setDushu(bodyJson.getString("ds"));
                    ktdg.save();
                    JSONObject jsonObject27 = new JSONObject();
                    jsonObject27.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject27.toString());
                case "/get_hjzb":
                    Random random1 = new Random();
                    JSONObject jsonObject25 = new JSONObject();
                    JSONArray jsonArray12 = new JSONArray();

                    int ql1 = random1.nextInt(50);
                    int qjd1 = random1.nextInt(50);
                    int sd1 = random1.nextInt(50);
                    int gz1 = random1.nextInt(50);
                    int wd1 = random1.nextInt(50);
                    int dl1 = random1.nextInt(100);
                    int dl21 = random1.nextInt(50);
                    int ylrl = random1.nextInt(500);
                    int qcrl = random1.nextInt(500);


                    JSONObject jsonObject26 = new JSONObject();

                    jsonObject26.put("ql", ql1);
                    jsonObject26.put("qjd", qjd1);
                    jsonObject26.put("sd", sd1);

                    jsonObject26.put("gz", gz1);
                    jsonObject26.put("wd", wd1);
                    if (dl1 > dl21) {

                        jsonObject26.put("dl", dl1);
                        jsonObject26.put("xh", dl21);

                    } else {
                        jsonObject26.put("xh", dl21);
                        jsonObject26.put("dl", dl1);
                    }
                    jsonObject26.put("yl", ylrl);
                    jsonObject26.put("qc", qcrl);

                    jsonArray12.put(jsonObject26);

                    jsonObject25.put("ROWS_DETAIL", jsonArray12);
                    jsonObject25.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject25.toString());


                case "/get_stock_shipment":
                    List<Shipment1> shipments = LitePal.findAll(Shipment1.class);
                    JSONArray jsonArray9 = new JSONArray();
                    for (int i = 0; i < shipments.size(); i++) {
                        Shipment1 shipment = shipments.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name", shipment.getName());
                        jsonObject.put("xh", shipment.getXh());
                        jsonObject.put("shuliang", shipment.getShuliang());
                        jsonObject.put("time", shipment.getTime());
                        jsonObject.put("chr", shipment.getChr());
                        jsonObject.put("jsr", shipment.getJsr());
                        jsonObject.put("qx", shipment.getQx());
                        jsonObject.put("path", shipment.getPath());
                        jsonObject.put("scx", shipment.getScx());
                        jsonArray9.put(jsonObject);
                    }
                    JSONObject jsonObject18 = new JSONObject();
                    jsonObject18.put("ROWS_DETAIL", jsonArray9);
                    jsonObject18.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject18.toString());

                case "/set_stock_shipment":

                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);

                    Shipment1 shipment = new Shipment1();
                    shipment.setName(bodyJson.getString("name"));
                    shipment.setXh(bodyJson.getString("xh"));
                    shipment.setShuliang(bodyJson.getString("shuliang"));
                    shipment.setTime(bodyJson.getString("time"));
                    shipment.setChr(bodyJson.getString("chr"));
                    shipment.setJsr(bodyJson.getString("jsr"));
                    shipment.setQx(bodyJson.getString("qx"));
                    shipment.setPath(bodyJson.getString("path"));
                    shipment.setScx(bodyJson.getString("scx"));
                    shipment.save();
                    JSONObject jsonObject17 = new JSONObject();
                    jsonObject17.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject17.toString());

                case "/get_stock_warehousing":
                    List<Warehousing> warehousings = LitePal.findAll(Warehousing.class);
                    JSONArray jsonArray8 = new JSONArray();
                    for (int i = 0; i < warehousings.size(); i++) {
                        Warehousing warehousing = warehousings.get(i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name", warehousing.getName());
                        jsonObject.put("xh", warehousing.getXh());
                        jsonObject.put("gys", warehousing.getGys());
                        jsonObject.put("shuliang", warehousing.getShuliang());
                        jsonObject.put("dj", warehousing.getDj());
                        jsonObject.put("weizhi", warehousing.getWeizhi());
                        jsonObject.put("caigoyuan", warehousing.getCaigoyuan());
                        jsonObject.put("lianxiren", warehousing.getLianxiren());
                        jsonObject.put("zhanghao", warehousing.getZhanghao());
                        jsonObject.put("ren", warehousing.getRen());
                        jsonObject.put("time", warehousing.getTime());
                        jsonObject.put("path", warehousing.getPath());
                        jsonArray8.put(jsonObject);
                    }
                    JSONObject jsonObject16 = new JSONObject();
                    jsonObject16.put("ROWS_DETAIL", jsonArray8);
                    jsonObject16.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject16.toString());
                case "/set_stock_warehousing":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);

                    Warehousing warehousing = new Warehousing();
                    warehousing.setName(bodyJson.getString("name"));
                    warehousing.setXh(bodyJson.getString("xh"));
                    warehousing.setGys(bodyJson.getString("gys"));
                    warehousing.setShuliang(bodyJson.getString("shuliang"));
                    warehousing.setDj(bodyJson.getString("dj"));
                    warehousing.setWeizhi(bodyJson.getString("weizhi"));
                    warehousing.setCaigoyuan(bodyJson.getString("caigoyuan"));
                    warehousing.setLianxiren(bodyJson.getString("lianxiren"));
                    warehousing.setZhanghao(bodyJson.getString("zhanghao"));
                    warehousing.setRen(bodyJson.getString("ren"));
                    warehousing.setTime(bodyJson.getString("time"));
                    warehousing.setPath(bodyJson.getString("path"));
                    warehousing.save();
                    JSONObject jsonObject15 = new JSONObject();
                    jsonObject15.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject15.toString());

                case "/get_supplier_transaction":
                    List<Transaction1> transactions = LitePal.findAll(Transaction1.class);
                    JSONArray jsonArray7 = new JSONArray();
                    for (int i = 0; i < transactions.size(); i++) {
                        Transaction1 transaction = transactions.get(i);
                        JSONObject jsonObject5 = new JSONObject();
                        jsonObject5.put("csm", transaction.getChangshang());
                        jsonObject5.put("clm", transaction.getCailiaoming());
                        jsonObject5.put("time", transaction.getTime());
                        jsonObject5.put("dj", transaction.getDanjia());
                        jsonObject5.put("sl", transaction.getShuliang());
                        jsonObject5.put("zjine", transaction.getZongjine());
                        jsonObject5.put("zh", transaction.getZhanghao());
                        jsonObject5.put("cgy", transaction.getCaigoyuan());
                        jsonObject5.put("lxr", transaction.getLianxiren());
                        jsonArray7.put(jsonObject5);
                    }
                    JSONObject jsonObject14 = new JSONObject();
                    jsonObject14.put("ROWS_DETAIL", jsonArray7);
                    jsonObject14.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject14.toString());

                case "/set_supplier_transaction":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Transaction1 transaction = new Transaction1();
                    transaction.setChangshang(bodyJson.getString("csm"));
                    transaction.setCailiaoming(bodyJson.getString("clm"));
                    transaction.setTime(bodyJson.getString("time"));
                    transaction.setDanjia(bodyJson.getString("dj"));
                    transaction.setShuliang(bodyJson.getString("sl"));
                    transaction.setZongjine(bodyJson.getString("zjine"));
                    transaction.setZhanghao(bodyJson.getString("zh"));
                    transaction.setCaigoyuan(bodyJson.getString("cgy"));
                    transaction.setLianxiren(bodyJson.getString("lxr"));
                    transaction.save();
                    JSONObject jsonObject13 = new JSONObject();
                    jsonObject13.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject13.toString());
                case "/get_supplier_material":
                    List<MaterialA1> materials = LitePal.findAll(MaterialA1.class);
                    JSONArray jsonArray6 = new JSONArray();
                    for (int i = 0; i < materials.size(); i++) {
                        MaterialA1 material = materials.get(i);
                        JSONObject jsonObject5 = new JSONObject();
                        jsonObject5.put("path", material.getPath());
                        jsonObject5.put("name", material.getName());
                        jsonObject5.put("xh", material.getXinghao());
                        jsonObject5.put("cshang", material.getChangshang());
                        jsonObject5.put("cs", material.getChengshi());
                        jsonObject5.put("kcl", material.getKcl());
                        jsonObject5.put("wz", material.getWz());
                        jsonArray6.put(jsonObject5);
                    }
                    JSONObject jsonObject12 = new JSONObject();
                    jsonObject12.put("ROWS_DETAIL", jsonArray6);
                    jsonObject12.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject12.toString());
                case "/set_supplier_material":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);

                    MaterialA1 material = new MaterialA1();
                    material.setPath(bodyJson.getString("path"));
                    material.setName(bodyJson.getString("name"));
                    material.setXinghao(bodyJson.getString("xh"));
                    material.setChangshang(bodyJson.getString("cshang"));
                    material.setChengshi(bodyJson.getString("cs"));
                    material.setKcl(bodyJson.getString("kcl"));
                    material.setWz(bodyJson.getString("wz"));
                    material.save();
                    JSONObject jsonObject11 = new JSONObject();
                    jsonObject11.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject11.toString());
                //----------------------------------------------------------------------------------------------
                case "/get_factory_application":
                    List<Application> applications = LitePal.findAll(Application.class);
                    JSONArray jsonArray5 = new JSONArray();
                    for (int i = 0; i < applications.size(); i++) {
                        Application application = applications.get(i);
                        JSONObject jsonObject41 = new JSONObject();
                        jsonObject41.put("username", application.getUsername());
                        jsonObject41.put("path", application.getPath());
                        jsonObject41.put("time", application.getTime());
                        jsonArray5.put(jsonObject41);
                    }
                    JSONObject jsonObject9 = new JSONObject();
                    jsonObject9.put("ROWS_DETAIL", jsonArray5);
                    jsonObject9.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject9.toString());
                case "/set_factory_application":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Application application = new Application();
                    application.setUsername(bodyJson.getString("username"));
                    application.setPath(bodyJson.getString("path"));
                    application.setTime(bodyJson.getString("time"));
                    application.save();
                    JSONObject jsonObject8 = new JSONObject();
                    jsonObject8.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject8.toString());
                case "/get_factory_information":
                    List<Jbxxsql> jbxxes = LitePal.findAll(Jbxxsql.class);
                    JSONArray jsonArray4 = new JSONArray();
                    for (int i = 0; i < jbxxes.size(); i++) {
                        Jbxxsql jbxx = jbxxes.get(i);
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("name", jbxx.getName());
                        jsonObject3.put("sex", jbxx.getSex());
                        jsonObject3.put("zy", jbxx.getZy());
                        jsonObject3.put("xx", jbxx.getXx());
                        jsonObject3.put("email", jbxx.getEmail());
                        jsonObject3.put("tel", jbxx.getTel());
                        jsonObject3.put("csrq", jbxx.getCrjg());
                        jsonObject3.put("jg", jbxx.getJg());
                        jsonObject3.put("xl", jbxx.getXl());
                        jsonObject3.put("gzjl", jbxx.getGzjl());
                        jsonObject3.put("jyxx", jbxx.getJyxx());
                        jsonObject3.put("hj", jbxx.getHj());
                        jsonObject3.put("path", jbxx.getPath());
                        jsonObject3.put("user", jbxx.getUser());
                        jsonArray4.put(jsonObject3);
                    }
                    JSONObject jsonObject7 = new JSONObject();
                    jsonObject7.put("ROWS_DETAIL", jsonArray4);
                    jsonObject7.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject7.toString());

                case "/update_factory_information":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Jbxxsql jbxx1 = new Jbxxsql();
                    jbxx1.setName(bodyJson.getString("name"));
                    jbxx1.setSex(bodyJson.getString("sex"));
                    jbxx1.setZy(bodyJson.getString("zy"));
                    jbxx1.setXx(bodyJson.getString("xx"));
                    jbxx1.setEmail(bodyJson.getString("email"));
                    jbxx1.setTel(bodyJson.getString("tel"));
                    jbxx1.setCrjg(bodyJson.getString("csrq"));
                    jbxx1.setJg(bodyJson.getString("jg"));
                    jbxx1.setXl(bodyJson.getString("xl"));
                    jbxx1.setGzjl(bodyJson.getString("gzjl"));
                    jbxx1.setJyxx(bodyJson.getString("jyxx"));
                    jbxx1.setHj(bodyJson.getString("hj"));
                    jbxx1.setPath(bodyJson.getString("path"));
                    jbxx1.setUser(bodyJson.getString("user"));
                    jbxx1.updateAll("user=?", bodyJson.getString("user"));
                    JSONObject jsonObject10 = new JSONObject();
                    jsonObject10.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject10.toString());


                case "/set_factory_information":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    Jbxxsql jbxx = new Jbxxsql();
                    jbxx.setName(bodyJson.getString("name"));
                    jbxx.setSex(bodyJson.getString("sex"));
                    jbxx.setZy(bodyJson.getString("zy"));
                    jbxx.setXx(bodyJson.getString("xx"));
                    jbxx.setEmail(bodyJson.getString("email"));
                    jbxx.setTel(bodyJson.getString("tel"));
                    jbxx.setCrjg(bodyJson.getString("csrq"));
                    jbxx.setJg(bodyJson.getString("jg"));
                    jbxx.setXl(bodyJson.getString("xl"));
                    jbxx.setGzjl(bodyJson.getString("gzjl"));
                    jbxx.setJyxx(bodyJson.getString("jyxx"));
                    jbxx.setHj(bodyJson.getString("hj"));
                    jbxx.setPath(bodyJson.getString("path"));
                    jbxx.setUser(bodyJson.getString("user"));
                    jbxx.save();
                    JSONObject jsonObject5 = new JSONObject();
                    jsonObject5.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject5.toString());
                case "/set_login":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);

                    User2 user1 = new User2();
                    user1.setUsername(bodyJson.getString("username"));
                    user1.setPassword(bodyJson.getString("password"));
                    user1.setEmail(bodyJson.getString("email"));
                    user1.setJine("1000");
                    user1.save();
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject3.toString());
                case "/get_login":
                    List<User2> muser = LitePal.findAll(User2.class);
                    JSONArray jsonArray1 = new JSONArray();
                    for (int i = 0; i < muser.size(); i++) {
                        User2 user = muser.get(i);
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("username", user.getUsername());
                        jsonObject2.put("password", user.getPassword());
                        jsonObject2.put("email", user.getEmail());
                        jsonObject2.put("jine", user.getJine());
                        jsonArray1.put(jsonObject2);
                    }
                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("ROWS_DETAIL", jsonArray1);
                    jsonObject2.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject2.toString());
                case "/get_factory_info":
                    Log.d("111111111111111", "serve: ---" + appClient.getLight());
                    Random random = new Random();
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();

                    int wd = random.nextInt(40);

                    int dl = random.nextInt(150);

                    int dl2 = random.nextInt(120);

                    JSONObject jsonObject1 = new JSONObject();

                    jsonObject1.put("outWd", wd);

                    jsonObject1.put("intWd", (wd - 5));

                    if (dl > dl2) {

                        jsonObject1.put("butteryIn", dl);
                        jsonObject1.put("butteryOut", dl2);

                    } else {
                        jsonObject1.put("butteryIn", dl2);
                        jsonObject1.put("butteryOut", dl);
                    }
                    if (appClient.getLight()) {
                        jsonObject1.put("light", "开启");
                    } else {
                        jsonObject1.put("light", "关闭");
                    }

                    if (appClient.getAir()) {
                        jsonObject1.put("air", "冷风");
                    } else {
                        jsonObject1.put("air", "热风");
                    }

                    jsonObject1.put("kt", appClient.getKt());

                    jsonArray.put(jsonObject1);

                    jsonObject.put("ROWS_DETAIL", jsonArray);

                    jsonObject.put("RESULT", "S");

                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject.toString());

                case "/set_factory_air":

                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    if ("冷风".equals(bodyJson.getString("air"))) {
                        appClient.setLight(true);
                    } else if ("热风".equals(bodyJson.getString("air"))) {
                        appClient.setLight(false);
                    } else {
                        return null;
                    }

                    return newFixedLengthResponse(Response.Status.OK, "application/json", allJson.toString());

                case "/set_factory_light":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    if ("开启".equals(bodyJson.getString("light"))) {
                        appClient.setLight(true);
                    } else if ("关闭".equals(bodyJson.getString("light"))) {
                        appClient.setLight(false);
                    } else {
                        return null;
                    }
                    return newFixedLengthResponse(Response.Status.OK, "application/json", allJson.toString());
                case "/send_notifi_info":
                    session.parseBody(zMap);
                    body = zMap.get("postData");
                    bodyJson = new JSONObject(body);
                    TZ_SQL sql = new TZ_SQL(1, bodyJson.optString("name"), bodyJson.optString("msg"), SimpData.Simp(new Date(), "yyyy-MM-dd"));
                    if (sql.save()) {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", allJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", "{\"RESULT\": \"F\"}");
                    }
                case "/get_notifi_info":
                    List<TZ_SQL> tz_sqls = LitePal.where("state=?", "1").find(TZ_SQL.class);
                    System.out.println("aaaaaa");
                    JSONArray jsonArray20 = new JSONArray();
                    for (int i = 0; i < tz_sqls.size(); i++) {
                        TZ_SQL tz_sql = tz_sqls.get(i);
                        JSONObject jsonObject24 = new JSONObject();
                        jsonObject24.put("id", tz_sql.getId());
                        jsonObject24.put("name", tz_sql.getStris());
                        jsonObject24.put("nr", tz_sql.getNeirong());
                        jsonObject24.put("time", tz_sql.getTime());
                        jsonArray20.put(jsonObject24);
                    }
                    JSONObject jsonObject20 = new JSONObject();
                    jsonObject20.put("RESULT", "S");
                    jsonObject20.put("ROWS_DETAIL", jsonArray20);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject20.toString());
                case "/request_notif_info":
                    session.parseBody(zMap);
                    body = zMap.get("postData");
                    bodyJson = new JSONObject(body);
                    TZ_SQL tz_sql = new TZ_SQL();
                    tz_sql.setState(2);
                    tz_sql.setRequestInfo(bodyJson.optString("request"));
                    if (tz_sql.updateAll("id=?", bodyJson.optString("id")) != 0) {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", allJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", "{\"RESULT\": \"F\"}");
                    }

            }

        } catch (JSONException e) {

            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        return null;

    }
}
