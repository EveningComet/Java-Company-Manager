package org.eveningcomet.simplejavacompanymanager;

/**
 * Represents an employee in a business.
 */
public class Employee
{
    private String name;
    private String id;

    /**
     * Creates a new instance.
     * 
     * @param   nameToSet   The name for this employee.
     * @param   idToSet     The id for this employee.
     */
    public Employee(String nameToSet, String idToSet)
    {
        name = nameToSet;
        id   = idToSet;
    }

    /**
     * Get the name of this person.
     * 
     * @return  Returns the name of the person.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the id of this person.
     * 
     * @return  Returns the id of the person.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Update this employee's name.
     * 
     * @param   newName The new name for this employee.
     */
    public void changeName(String newName)
    {
        if(newName != null && newName.isEmpty() == false)
        {
            name = newName;
        }
    }

    /**
     * Update the id for this worker.
     * 
     * @param   newId   The new id for the user.
     */
    public void changeId(String newId)
    {
        if(newId != null && newId.isEmpty() == false)
        {
            id = newId;
        }
    }

    /**
     * Get a string with the basic details of the worker.
     * 
     * @return  Returns a string with basic info regarding this worker.
     */
    @Override public String toString()
    {
        return String.format("%s which has id %s", name, id);
    }
}