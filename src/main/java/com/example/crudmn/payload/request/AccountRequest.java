package com.example.crudmn.payload.request;

import javax.validation.constraints.NotBlank;

public class AccountRequest {


   // @NotBlank
    private int accNo;

   // @NotBlank
  //  private String accName;
   // @NotBlank
    private double amount;
   // @NotBlank
  //  private String currency;

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
