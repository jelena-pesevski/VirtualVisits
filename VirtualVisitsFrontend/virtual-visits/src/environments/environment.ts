// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  BASE_URL:"http://localhost:9000/",
  BANK_URL:"http://localhost:9001/transactions",
  TOKEN_KEY:"token",
  REFRESH_TOKEN_KEY:"rfrshTkn",
  ID_KEY:"id",
  LOGGED_IN_KEY:"isLogged",
  IS_ADMIN_KEY:"isAdmin",
  TOKEN_HEADER_KEY:"Authorization"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
