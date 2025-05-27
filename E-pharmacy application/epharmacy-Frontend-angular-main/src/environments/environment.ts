// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  //Write the necessary URLs to send request to backend APIs
  LoginUrl: "http://localhost:1100/epharmacy/login/Oauth",
  customerLoginUrl: "http://localhost:6100/epharmacy/customer-api/customer/login",
  getAllMedicinesUrl: "http://localhost:6200/epharmacy/medicine-api/medicines/pageNumber/",
  registerCustomerUrl: "http://localhost:6100/epharmacy/customer-api/customer/register",
  getCustomerCartUrl: "http://localhost:6100/epharmacy/customer-api/customer/",

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
