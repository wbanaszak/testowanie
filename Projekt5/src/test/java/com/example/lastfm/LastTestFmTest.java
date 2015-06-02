package com.example.lastfm;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.*;



public class LastTestFmTest {

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://ws.audioscrobbler.com/";
		RestAssured.basePath = "/2.0";
	}

	@Test
	public void searchArtist() {
		String artist = "Cher";
		get(
				"/?method=artist.search&artist=" + artist
						+ "&api_key=290a5af7fda95999bb75cf3bf2b17102").then()
				.body("lfm.results.artistmatches.artist.name[0]",
						equalTo(artist));
	}

	@Test
	public void searchArtist2() {
		String artist = "U2";
		get(
				"/?method=artist.search&artist=" + artist
						+ "&api_key=290a5af7fda95999bb75cf3bf2b17102").then()
				.body("lfm.results.artistmatches.artist.name[0]",
						equalTo(artist));
	}
}
