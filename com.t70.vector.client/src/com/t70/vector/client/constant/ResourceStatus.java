package com.t70.vector.client.constant;

/**
 * 
 The status of a resource.
 */
public enum ResourceStatus
{
    /**
     * The resource is fully active and can be used per normal.
     */
    ACTIVE(0),

    /**
     * The resource is no longer active and can no longer be used.
     */
    INACTIVE(1),

    /**
     * The resource has been deleted and cannot be used.
     */
    DELETED(2);

    private int m_resourceStatus;

    ResourceStatus(int resourceStatus)
    {
        m_resourceStatus = resourceStatus;
    }

    public int getValue()
    {
        return m_resourceStatus;
    }

    public static ResourceStatus resourceStatus(int resourceStatus)
    {
        ResourceStatus status = null;

        switch (resourceStatus)
        {
        case 0:
            status = ResourceStatus.valueOf("ACTIVE");
            break;

        case 1:
            status = ResourceStatus.valueOf("INACTIVE");
            break;

        case 2:
            status = ResourceStatus.valueOf("DELETED");
            break;

        }
        return status;
    }
}
