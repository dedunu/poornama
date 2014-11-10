package poornama.data.sample;

import com.poornama.data.dao.VehicleTypeDAO;
import com.poornama.api.objects.VehicleType;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dedunu on 11/8/14.
 */
@Ignore
public class VehicleTypeTest {

    @Test
    public void runTest() {
        VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
        VehicleType vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Prime Mover");
        vehicleType1.setName("prime_mover");
        vehicleTypeDAO.create(vehicleType1);

        vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Trailer");
        vehicleType1.setName("trailer");
        vehicleTypeDAO.create(vehicleType1);

        vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Cab");
        vehicleType1.setName("cab");
        vehicleTypeDAO.create(vehicleType1);

        vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Van");
        vehicleType1.setName("van");
        vehicleTypeDAO.create(vehicleType1);
    }
}
