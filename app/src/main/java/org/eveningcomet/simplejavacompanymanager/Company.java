package org.eveningcomet.simplejavacompanymanager;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Represents a business with a set of employees.
 */
public class Company
{
    private final int MIN = 100;
    private final int MAX = 999;
    
    private String name;
    private final HashMap<String, Employee> employees;
    
    private final Random randomGenerator;
    private final static Logger logger = Logger.getLogger(Company.class.getName());
    
    /**
     * Create a brand new company, taking a name.
     * 
     * @param   nameToSet    The name for the new company.
     */
    public Company(String nameToSet)
    {
        name = formatString(nameToSet);
        employees = new HashMap<String, Employee>();
        randomGenerator = new Random();
    }
    
    /**
     * Get the name of the company.
     * 
     * @return  Returns the name of the company.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the people working for this company.
     * 
     * @return  Returns the employees attached to this company.
     */
    public HashMap<String, Employee> getEmployees()
    {
        return employees;
    }
    
    /**
     * See how many people work for this company.
     * 
     * @return  Returns the amount of people working in the company.
     */
    public int getTotalNumberOfEmployees()
    {
        return employees.size();
    }

    /**
     * Update the name for this business.
     * 
     * @param   newName The new name to set for the business.
     */
    public void changeName(String newName)
    {
        newName = formatString(newName);
        if(newName.isEmpty())
        {
            logger.log(Level.WARNING, "The new name is INVALID");
        }
        else
        {
            name = newName;
            logger.log(Level.INFO, String.format("The new name is now: %s", name));
        }
    }
    
    /**
     * Cleans up the passed string so that it works better with the program.
     * 
     * @param   origString  The desired text that needs to be cleaned up.
     * @return  Returns the cleaned up string.
     */
    private String formatString(String origString)
    {
        return origString == null ? "" : origString.trim().toUpperCase().replaceAll("\\s+", " ");
    }
    
    /**
     * Generate an Id, using someone's name.
     * 
     * @param   name    The name for the new employee.
     * @return  Returns a new Id.
     */
    private String generateId(String name)
    {
        String[] nameArray = formatString(name).split(" ");
        String id = "";
        
        for (String word : nameArray) {
            if(word.isEmpty() == false)
            {
                id += word.substring(0, 1);
            }
        }
        
        id += randomGenerator.nextInt(MAX - MIN + 1) + MIN;
        
        return id;
    }

    /**
     * Add a new employee to the business.
     * 
     * @param   inputName   The name of the new employee.
     */
    public void addEmployee(String inputName)
    {
        inputName = formatString(inputName);
        if(inputName.isEmpty())
        {
            logger.log(Level.WARNING, "Name is INVALID");
        }
        else
        {
            String id = "";
            do
            {
                id = generateId(inputName);
            } while(employees.containsKey(id) == true);

            employees.put(id, new Employee(inputName, id));
            logger.log(Level.INFO, String.format("Successfully added %s with id %s", inputName, id));
        }
    }

    /**
     * Attempt to find and remove the employee with the passed name.
     * 
     * @param   inputName   The name of the employee the user wants to remove.
     */
    public void removeEmployeeByName(String inputName)
    {
        inputName = formatString(inputName);
        if(inputName.isEmpty())
        {
            logger.log(Level.WARNING, "Name is INVALID");
        }
        else
        {
            boolean found = false;

            for(Iterator<String> it = employees.keySet().iterator(); it.hasNext(); )
            {
                String employeeId = it.next();
                Employee employee = employees.get(employeeId);

                if(employee.getName().equalsIgnoreCase(inputName))
                {
                    // The iterator is smart enough to remove from the underlying Hash Map
                    it.remove();

                    logger.log(Level.INFO, String.format("Successfully removed %s", employee.toString()));
                    found = true;
                }
            }

            if(!found)
            {
                logger.log(Level.INFO, String.format("Nobody found with name: %s", inputName));
            }
        }
    }

    /**
     * Remove a person from the business.
     * 
     * @param   id  The desired id to remove. 
     */
    public void removeEmployeeById(String id)
    {
        id = formatString(id);
        if(id.isEmpty())
        {
            logger.log(Level.WARNING, "Id is INVALID");
        }
        else
        {
            if(employees.containsKey(id))
            {
                logger.log(Level.INFO, String.format("Nobody found with id: %s", id));
            }
            else
            {
                Employee removedEmployee = employees.remove(id);
                logger.log(Level.INFO, String.format("Removed %s", removedEmployee.toString()));
            }
        }
    }

    /**
     * Output the people working in the company.
     */
    public void listEmployees()
    {
        logger.log(Level.INFO, String.format("Employees for %s:", name));
        if(employees.isEmpty())
        {
            logger.log(Level.INFO, String.format("There are no employees for %s", name));
        }
        else
        {
            for(String employeeKey : employees.keySet())
            {
                logger.log(Level.INFO, String.format("\t%s", employees.get(employeeKey).toString()));
            }
        }
    }
}