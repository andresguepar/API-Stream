package domain.models;

import domain.enums.ClientType;

public class Customer {
    private long clientId;
    private String name;
    private ClientType tier;

    public Customer(long id, String name, ClientType tier) {
        this.clientId = id;
        this.name = name;
        this.tier = tier;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getTier() {
        return tier;
    }

    public void setTier(ClientType tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + clientId +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
