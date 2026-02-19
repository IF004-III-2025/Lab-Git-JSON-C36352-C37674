package edu.ucr.mvcjson.model;

public enum Species {
    DOG,CAT,BIRD,RABBIT,OTHER;

     public String valueOf() {
         return this.name();
     }
}
