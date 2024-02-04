class validateCredentials extends UserCredentials {
    public static boolean checkCredentials(int cmsInput,String password){

        boolean passwordincorrect = true; //logical layer to process the data selected from the table
        boolean cmsincorrect = true;
        System.out.println(arrayCMS);
        while (cmsincorrect) {

            System.out.println("Enter your CMS ID: ");
            if (arrayCMS.contains(cmsInput)) {
                cmsincorrect = false;
            }
            else{
                System.out.println("Invalid username. Try again.");
                return false;
            }
        }
        while (passwordincorrect) {
            System.out.print("Enter your password: ");
            if (password.equals(arrayPassword.get(arrayCMS.indexOf(cmsInput)))) {
                passwordincorrect = false;


            } else {
                System.out.println("Password incorrect.Try again.");
                return false;
            }

        }
        if ((!passwordincorrect) && (!cmsincorrect)){
            System.out.println("true");
            return true;
        }
        System.out.println("false");
        return true;
    }
}
