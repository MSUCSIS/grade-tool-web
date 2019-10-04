package edu.murraystate.csis.gradetool.web;

import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

public class App {
  public static void main(String[] args) {
    Javalin app = Javalin.create(config->{
      config.addStaticFiles("/web-resources");
    }).start(7000);

    app.get("/", ctx -> {
      final String accepts = ctx.header("Accept");
      if(accepts!=null && accepts.toLowerCase().contains("text/html")){
        final Map<String,Object> model = new HashMap<>();
        model.put("title","MSU Homework Machine");
        model.put("contents","Welcome to the MSU online homework grader");
        ctx.render("/templates/html/main.vm", model);
      }else{
        ctx.render("/templates/json/hello-world.vm");
      }
    });
  }
}
