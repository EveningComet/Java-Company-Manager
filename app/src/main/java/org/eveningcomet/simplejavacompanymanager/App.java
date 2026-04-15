package org.eveningcomet.simplejavacompanymanager;

public class App
{
    public static void main(String[] args)
    {
        Company myCompany = new Company("Stockton Publishing");
        myCompany.addEmployee("Doug Walker");
        myCompany.addEmployee("Terry Davis");
        myCompany.addEmployee("Katie Bella");
        myCompany.addEmployee("Ezri Dax");
        myCompany.listEmployees();
    }
}
