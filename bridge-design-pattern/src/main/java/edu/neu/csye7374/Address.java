package edu.neu.csye7374;

public class Address {
   private String street;
   private String city;
   private String state;
   private String zipcode;

   private Address(String street, String city, String state, String zipcode) {
      this.street = street;
      this.city = city;
      this.state = state;
      this.zipcode = zipcode;
   }

   public String getStreet() {
      return street;
   }

   public String getCity() {
      return city;
   }

   public String getState() {
      return state;
   }

   public String getZipcode() {
      return zipcode;
   }

   public static class Builder {
      private String street;
      private String city;
      private String state;
      private String zipcode;

      public Builder() {
      }

      public Builder setStreet(String street) {
         this.street = street;
         return this;
      }

      public Builder setCity(String city) {
         this.city = city;
         return this;
      }

      public Builder setState(String state) {
         this.state = state;
         return this;
      }

      public Builder setZipcode(String zipcode) {
         this.zipcode = zipcode;
         return this;
      }

      public Address build() {
         return new Address(street, city, state, zipcode);
      }
   }
}
