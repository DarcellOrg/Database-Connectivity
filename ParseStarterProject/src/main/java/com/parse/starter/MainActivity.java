/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /*

      ParseObject sample = new ParseObject("coolLevel");

      sample.put("username", "Darcell");

      sample.put("coolLevel", 85);

      sample.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {

              //this method will be called write after the save is complete, whether successful or a fail
              if (e == null){

                  Log.i("SaveInBackground", "The save was a success");
              }else {

                  Log.i("SaveInBackground", "The save was a failure. Fail Report: " + e.toString());

              }
          }
      });
   */



/*      //How to bring back date from the server, bad way, with knowing the object id
      ParseQuery<ParseObject> query = ParseQuery.getQuery("coolLevel");

      query.getInBackground("lxLvGtkuUS", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {

              if (e == null && object != null){

                  //updated locally
                  object.put("coolLevel", 86);
                  //now updated globally, app wide
                  object.saveInBackground();

                  Log.i("PersonName", object.getString("username"));

                  Log.i("CoolLevel", Integer.toString(object.getInt("coolLevel")));

              }
          }
      });*/

    // How to get  values on a class, class=coolLevel

      ParseQuery<ParseObject> query = ParseQuery.getQuery("coolLevel");

      query.whereGreaterThan("coolLevel", 80);//we wish to update all objects that match the predicate so no limit here

      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {

              if ( e == null && objects != null){

                  for (ParseObject object : objects){

                      object.put("coolLevel", object.getInt("coolLevel") + 5);//increase cool level
                      object.saveInBackground();//udpade the server
                      Log.i("findInBackgroundResult", object.getInt("coolLevel") +" is the cool level for: " + object.getString("username"));
                  }
              }
          }
      });

/*      //to get a single value add a search criteria
      query.whereEqualTo("username", "Garen");

      query.setLimit(1);//just in case always try to limit the changes made to 1

      query.findInBackground(new FindCallback<ParseObject>() {
          @Override

          public void done(List<ParseObject> objects, ParseException e) { //our returned info is given in a list<ParseObjects>

             if( e == null){

                 Log.i("findInBackground", "Retrieved " + objects.size() + " objects");

                 if(objects.size() > 0) {
                     //this for loop will tell us all the objects using toString
                     for (ParseObject object : objects) {

                         Log.i("findInBackgroundResult", object.getInt("coolLevel") +" is the cool level for: " + object.getString("username"));
                     }
                 }
             }

          }
      });*/

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}