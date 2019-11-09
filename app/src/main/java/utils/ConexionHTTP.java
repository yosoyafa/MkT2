package utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConexionHTTP {

    //SERVIDORES
    public final static String SERVER = "https://cc.integracionip.com/ws_cc/";
    private static final String SERVER_IBAGUE = "";

    //SEDES
    private static final String LLANO = "llano";
    private static final String IBAGUE = "ibague";

    //LOGIN SERVER
    public final static String LOGIN = "https://integracionip.com/app/getUser.php?user_name=";

    //Links LLANO
    public final static String DOWNLOAD_CARTERA = "getCartera1.php?user_id=";
    public final static String RECAUDO = "getRecaudos.php?user_name=";
    public final static String BUSCAR_CC_EN_CARTERA = "getCarterabyCedula.php?user_id=";
    public final static String GESTION = "getGestionCartera.php?";
    public final static String UPDATE = "getUpdateInfo.php?";
    public final static String HISTORY = "getReimprimeRecaudos.php?";
    public final static String DOWNLOAD_AGENDA = "getAgenda.php?user_id=";
    public final static String AGENDAR_VISITA = "agendarVisita.php?user_name=";
    public final static String VISITA_REALIZADA = "getUpdateAgenda.php?user_name=";

    public final static String DOWNLOAD_CCS = "getLocal.php?user_id=2345";
    public final static String ACTUALIZACION = "updateLocal.php?";

    private JSONObject response;
    private JSONArray responseArray;
    private boolean finishProcess;
    private HttpURLConnection urlConnection;
    private String dataString;
    private String sede;

    public ConexionHTTP() {
        finishProcess = false;
    }

    public ConexionHTTP(String sede) {
        finishProcess = false;
        this.sede = sede;
        System.out.println(sede);
    }

    public JSONObject getRespuesta() {
        return response;
    }

    public boolean isFinishProcess() {
        return finishProcess;
    }

    public JSONArray getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(JSONArray responseArray) {
        this.responseArray = responseArray;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    private class ConectionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String data = "";
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                //httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("Content-Type", "application/json");
                //httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);

                //DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                //wr.writeBytes(params[1]);
                //wr.flush();
                //wr.close();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                }

                try {
                    finishProcess = true;
                    //System.out.println("--------------------\n1er char: "+data.charAt(0));
                    if (data.charAt(0) == '[') {
                        JSONArray ja = new JSONArray(data);
                        response = new JSONObject();
                        response.put("array", ja);
                    } else {
                        response = new JSONObject(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (java.net.UnknownHostException ex) {
                data = "no internet";
                finishProcess = true;
                ex.printStackTrace();
            } catch (Exception e) {
                data = "no internet";
                finishProcess = true;
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
        }
    }

    public void login(String user, String lat, String lon) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fecha = df.format(Calendar.getInstance().getTime());
        try {
            JSONObject post = new JSONObject();
            post.put("user_name", user);
            post.put("latitud", lat);
            post.put("longitud", lon);
            post.put("fecha", fecha);
            Log.d("url", LOGIN + user + "&latitud=" + lat + "&longitud=" + lon + "&fecha=" + fecha);
            new ConectionTask().execute(LOGIN + user + "&latitud=" + lat + "&longitud=" + lon + "&fecha=" + fecha, post.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void downloadCCs(String id) {
        try {
            JSONObject post = new JSONObject();
            post.put("id", id);
            System.out.println(SERVER + DOWNLOAD_CCS + id);
            new ConectionTaskMulti().execute(SERVER + DOWNLOAD_CCS + id, post.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void gestion(String idLocal, String centroComercial, String nombreActual, String nombreNuevo, String areaActual, String areaNueva, String categoriaActual, String categoriaNueva, String subcategoriaActual, String subcategoriaNueva, String tipoBienActual, String tipoBienNuevo, String observacion) {
        try {
            JSONObject post = new JSONObject();
            post.put("nombreActual", nombreActual);
            post.put("nombreNuevo", nombreNuevo);
            post.put("areaActual", areaActual);
            post.put("areaNueva", areaNueva);
            post.put("categoriaActual", categoriaActual);
            post.put("categoriaNueva", categoriaNueva);
            post.put("subcategoriaActual", subcategoriaActual);
            post.put("subcategoriaNueva", subcategoriaNueva);
            post.put("tipoBienActual", tipoBienActual);
            post.put("tipoBienNuevo", tipoBienNuevo);
            post.put("observacion", observacion);
            System.out.println("URL:" + SERVER + ACTUALIZACION + "idLocal=" + idLocal + "&centroComercial=" + centroComercial + "&nombreActual=" + nombreActual + "&nombreNuevo=" + nombreNuevo + "&areaActual=" + areaActual + "&areaNueva=" + areaNueva + "&categoriaActual=" + categoriaActual + "&categoriaNueva=" + categoriaNueva + "&subcategoriaActual=" + subcategoriaActual + "&subcategoriaNueva=" + subcategoriaNueva + "&tipoBienActual=" + tipoBienActual + "&tipoBienNuevo=" + tipoBienNuevo + "&observacion=" + observacion);
            new ConectionTask().execute(SERVER + ACTUALIZACION + "idLocal=" + idLocal + "&centroComercial=" + centroComercial + "&nombreActual=" + nombreActual + "&nombreNuevo=" + nombreNuevo + "&areaActual=" + areaActual + "&areaNueva=" + areaNueva + "&categoriaActual=" + categoriaActual + "&categoriaNueva=" + categoriaNueva + "&subcategoriaActual=" + subcategoriaActual + "&subcategoriaNueva=" + subcategoriaNueva + "&tipoBienActual=" + tipoBienActual + "&tipoBienNuevo=" + tipoBienNuevo + "&observacion=" + observacion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void visitaRealizada(String id_usuario, String idCRM, String fecha, String afiliados, String retirados, String resultado, String latitud, String longitud, String estado) {
        try {
            JSONObject post = new JSONObject();
            post.put("user_name", id_usuario);
            post.put("idCRM", idCRM);
            post.put("fecha", fecha);
            post.put("afiliados", afiliados);
            post.put("retirados", retirados);
            post.put("resultado", resultado);
            post.put("latitud", latitud);
            post.put("longitud", longitud);
            post.put("estado", estado);
            if (sede.equals(LLANO)) {
                System.out.println("URL:" + SERVER + VISITA_REALIZADA + id_usuario + "&idCRM=" + idCRM + "&fecha=" + fecha + "&afiliados=" + afiliados + "&retirados=" + retirados + "&resultado=" + resultado + "&latitud=" + latitud + "&longitud=" + longitud + "&estado=" + estado);
                new ConectionTask().execute(SERVER + VISITA_REALIZADA + id_usuario + "&idCRM=" + idCRM + "&fecha=" + fecha + "&afiliados=" + afiliados + "&retirados=" + retirados + "&resultado=" + resultado + "&latitud=" + latitud + "&longitud=" + longitud + "&estado=" + estado);

            } else if (sede.equals(IBAGUE)) {
                System.out.println("URL:" + SERVER_IBAGUE + VISITA_REALIZADA + id_usuario + "&idCRM=" + idCRM + "&fecha=" + fecha + "&afiliados=" + afiliados + "&retirados=" + retirados + "&resultado=" + resultado + "&latitud=" + latitud + "&longitud=" + longitud + "&estado=" + estado);
                new ConectionTask().execute(SERVER_IBAGUE + VISITA_REALIZADA + id_usuario + "&idCRM=" + idCRM + "&fecha=" + fecha + "&afiliados=" + afiliados + "&retirados=" + retirados + "&resultado=" + resultado + "&latitud=" + latitud + "&longitud=" + longitud + "&estado=" + estado);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void buscarCCenCartera(String id, String cc) {
        try {
            JSONObject post = new JSONObject();
            post.put("id", id);
            post.put("cc", cc);
            if (sede.equals(LLANO)) {
                System.out.println(SERVER + BUSCAR_CC_EN_CARTERA + id + "&NumeroDocumento=" + cc);
                new ConectionTaskMulti().execute(SERVER + BUSCAR_CC_EN_CARTERA + id + "&NumeroDocumento=" + cc, post.toString());
            } else if (sede.equals(IBAGUE)) {
                System.out.println(SERVER_IBAGUE + BUSCAR_CC_EN_CARTERA + id + "&NumeroDocumento=" + cc);
                new ConectionTaskMulti().execute(SERVER_IBAGUE + BUSCAR_CC_EN_CARTERA + id + "&NumeroDocumento=" + cc, post.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void recaudo(String user_name, String lat, String lon, String numerodocumento, String valorrecaudo, String id, String fecha, String numerador_rc, String observaciones, String fdp) {
        try {
            JSONObject post = new JSONObject();
            post.put("user_name", user_name);
            post.put("latitud", lat);
            post.put("longitud", lon);
            post.put("numerodocumento", numerodocumento);
            post.put("valorrecaudo", valorrecaudo);
            post.put("id", id);
            post.put("fecha_hora", fecha);
            post.put("numerador_rc", numerador_rc);
            post.put("observaciones", observaciones);
            post.put("forma_de_pago", fdp);
            if (sede.equals(LLANO)) {
                System.out.println("URL:" + SERVER + RECAUDO + user_name + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + numerodocumento + "&valorrecaudo=" + valorrecaudo + "&id=" + id + "&rc=" + numerador_rc + "&fecha_hora=" + fecha + "&detallerecaudo=" + observaciones + "&forma_de_pago=" + fdp);
                new ConectionTask().execute(SERVER + RECAUDO + user_name + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + numerodocumento + "&valorrecaudo=" + valorrecaudo + "&id=" + id + "&rc=" + numerador_rc + "&fecha_hora=" + fecha + "&detallerecaudo=" + observaciones + "&forma_de_pago=" + fdp);

            } else if (sede.equals(IBAGUE)) {
                System.out.println("URL:" + SERVER_IBAGUE + RECAUDO + user_name + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + numerodocumento + "&valorrecaudo=" + valorrecaudo + "&id=" + id + "&rc=" + numerador_rc + "&fecha_hora=" + fecha + "&detallerecaudo=" + observaciones + "&forma_de_pago=" + fdp);
                new ConectionTask().execute(SERVER_IBAGUE + RECAUDO + user_name + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + numerodocumento + "&valorrecaudo=" + valorrecaudo + "&id=" + id + "&rc=" + numerador_rc + "&fecha_hora=" + fecha + "&detallerecaudo=" + observaciones + "&forma_de_pago=" + fdp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void edicion(String user, String id, String nombre, String cedula, String tel1viejo, String tel1nuevo, String tel2viejo, String tel2nuevo, String direccionVieja, String direccionNueva, String fecha, String lat, String lon) {
        try {
            JSONObject post = new JSONObject();
            post.put("user", user);
            post.put("id", id);
            post.put("nombre", nombre);
            post.put("cedula", cedula);
            post.put("tel1viejo", tel1viejo);
            post.put("tel1nuevo", tel1nuevo);
            post.put("tel2viejo", tel2viejo);
            post.put("tel2nuevo", tel2nuevo);
            post.put("direccion_vieja", direccionVieja);
            post.put("direccion_nuevo", direccionNueva);
            post.put("fecha", fecha);
            post.put("latitud", lat);
            post.put("longitud", lon);
            if (sede.equals(LLANO)) {
                System.out.println("URL:" + SERVER + UPDATE + "user_name=" + user + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + cedula + "&fecha=" + fecha + "&user_id=" + id + "&celular1_o=" + tel1viejo + "&celular1_n=" + tel1nuevo + "&celular2_o=" + tel2viejo + "&celular2_n=" + tel2nuevo + "&direccion_o=" + direccionVieja + "&direccion_n=" + direccionNueva);
                System.out.println("-------------\nDireccion nueva: " + direccionNueva + "------------");
                new ConectionTask().execute(SERVER + UPDATE + "user_name=" + user + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + cedula + "&fecha=" + fecha + "&user_id=" + id + "&celular1_o=" + tel1viejo + "&celular1_n=" + tel1nuevo + "&celular2_o=" + tel2viejo + "&celular2_n=" + tel2nuevo + "&direccion_o=" + direccionVieja + "&direccion_n=" + direccionNueva);
            } else if (sede.equals(IBAGUE)) {
                System.out.println("URL:" + SERVER_IBAGUE + UPDATE + "user_name=" + user + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + cedula + "&fecha=" + fecha + "&user_id=" + id + "&celular1_o=" + tel1viejo + "&celular1_n=" + tel1nuevo + "&celular2_o=" + tel2viejo + "&celular2_n=" + tel2nuevo + "&direccion_o=" + direccionVieja + "&direccion_n=" + direccionNueva);
                System.out.println("-------------\nDireccion nueva: " + direccionNueva + "------------");
                new ConectionTask().execute(SERVER_IBAGUE + UPDATE + "user_name=" + user + "&latitud=" + lat + "&longitud=" + lon + "&numerodocumento=" + cedula + "&fecha=" + fecha + "&user_id=" + id + "&celular1_o=" + tel1viejo + "&celular1_n=" + tel1nuevo + "&celular2_o=" + tel2viejo + "&celular2_n=" + tel2nuevo + "&direccion_o=" + direccionVieja + "&direccion_n=" + direccionNueva);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getHistory(String user, String fecha) {
        try {
            JSONObject post = new JSONObject();
            post.put("user", user);
            post.put("fecha", fecha);
            if (sede.equals(LLANO)) {
                System.out.println("URL:" + SERVER + HISTORY + "user_name=" + user + "&fecha_hora=" + fecha);
                new ConectionTaskMulti().execute(SERVER + HISTORY + "user_name=" + user + "&fecha_hora=" + fecha);
            } else if (sede.equals(IBAGUE)) {
                System.out.println("URL:" + SERVER_IBAGUE + HISTORY + "user_name=" + user + "&fecha_hora=" + fecha);
                new ConectionTaskMulti().execute(SERVER_IBAGUE + HISTORY + "user_name=" + user + "&fecha_hora=" + fecha);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class ConectionTaskMulti extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String data = "";
            HttpURLConnection httpURLConnection = null;
            try {
                System.out.println("--------------------\nEmpezó");
                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                //httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("Content-Type", "application/json");
                //httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);

                //DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                //wr.writeBytes(params[1]);
                //wr.flush();
                //wr.close();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

//                int inputStreamData = inputStreamReader.read();
//                System.out.println("--------------------\nInicio del while");
//                while (inputStreamData != -1) {
//                    char current = (char) inputStreamData;
//                    inputStreamData = inputStreamReader.read();
//                    data += current;
//                }


                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                data = sb.toString();


                try {
                    finishProcess = true;
                    System.out.println("--------------------\nDescargó");
                    System.out.println(data);
                    dataString = data;
                    responseArray = new JSONArray(data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (java.net.UnknownHostException ex) {
                data = "no internet";
                finishProcess = true;
                ex.printStackTrace();
            } catch (Exception e) {
                data = "no internet";
                finishProcess = true;
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
        }
    }
}
