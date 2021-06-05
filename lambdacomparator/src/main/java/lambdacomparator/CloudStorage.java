package lambdacomparator;

public class CloudStorage implements Comparable<CloudStorage> {

    private final String provider;
    private final int space;
    private final PayPeriod period;
    private final double price;


    public CloudStorage(String provider, int space, PayPeriod period, double price) {
        this.provider = provider;
        this.space = space;
        this.period = period;
        this.price = price;
    }

    public CloudStorage(String provider, int space) {
        this.provider = provider;
        this.space = space;
        this.period = null;
        this.price = 0;
    }

    public String getProvider() {
        return provider;
    }

    public int getSpace() {
        return space;
    }

    public PayPeriod getPeriod() {
        return period;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CloudStorage{" +
                "provider='" + provider + '\'' +
                ", space=" + space +
                ", payPeriod=" + period +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(CloudStorage cs) {
        double currentValue = this.period != null ? this.price * 12 / this.period.getLength() / (this.space * 1000) : 0;
        double actualValue = cs.period != null ? cs.price * 12 / cs.period.getLength() / (cs.space * 1000) : 0;
        return Double.compare(currentValue, actualValue);
    }
}
