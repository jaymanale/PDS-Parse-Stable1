package com.teamtreehouse.parseworkshop;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class ParseWorkshopApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		/*
		 * Add Parse initialization code here
		 */

		// TODO: Add your own application ID and client key!
		Parse.initialize(this, "thvrV05LHgcUwFTpPVeW4ccdg5mgpjtZfqVnaSSs", "zWI3lngSU08PzYiB4ORMhk6m1AV61Qajwx1ABav1");

		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}
}
