package tufastly;

import org.hibernate.Session;
import tufastly.model.*;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.ArrayList;
import java.util.List;


public class OptimizedRoutineTest {
    private Coordinate setCoordinate(double latitude, double longitude) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(latitude);
        coordinate.setLongitude(longitude);
        return coordinate;
    }

    public static void main(String[] args) {
        OptimizedRoutineTest optimizedRoutineTest = new OptimizedRoutineTest();
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // TODO: 1. Define three centers
        CenterAddress centerAddress = new CenterAddress();
        centerAddress.setCenterAddress1("Asian Art Museum, CA");
        centerAddress.setCenterAddress2("San Francisco Cable Car Museum, CA");
        centerAddress.setCenterAddress3("San Francisco Museum of Modern Art, CA");
        session.save(centerAddress);

        // TODO: 2. Add five robots and drones to each center
        for (int i = 0; i < 15; i++) {
            Drone drone = new Drone();
            Robot robot = new Robot();
            if (i < 5) {
                drone.setDepartureCenter("Asian Art Museum, CA");
                robot.setDepartureCenter("Asian Art Museum, CA");
            } else if (i < 10) {
                drone.setDepartureCenter("San Francisco Cable Car Museum, CA");
                robot.setDepartureCenter("San Francisco Cable Car Museum, CA");
            } else {
                drone.setDepartureCenter("San Francisco Museum of Modern Art, CA");
                robot.setDepartureCenter("San Francisco Museum of Modern Art, CA");
            }
            drone.setDroneId(i);
            drone.setVelocity(100);
            drone.setUnitCost(0.01);
            drone.setArriveCenter(null);
            drone.setCurrentLocation(null);
            drone.setStatus("Charging");
            robot.setRobotId(i);
            robot.setVelocity(50);
            robot.setUnitCost(0.005);
            robot.setArriveCenter(null);
            robot.setCurrentLocation(null);
            robot.setStatus("Charging");
            session.save(drone);
            session.save(robot);
        }

        // TODO: 3. Create an half-done robot OrderInfo for testing RouteOptimization/Admin order endpoint
        OrderInfo orderInfo = new OrderInfo();
        PickUpAddress pickUpAddress = new PickUpAddress();
        pickUpAddress.setAddress("Contemporary Jewith Museum, CA");
        pickUpAddress.setCity("San Francisco");
        pickUpAddress.setCountry("US");
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setAddress("Ferry Building, CA");
        shippingAddress.setCity("San Francisco");
        shippingAddress.setCountry("US");
        orderInfo.setPickUpAddress(pickUpAddress);
        orderInfo.setShippingAddress(shippingAddress);
        orderInfo.setPolyline1("_}qeF~pdjVaAHNrB`@tG`AbPBf@c@D{@JqFp@wGx@kWzCid@pFs\\~DkOfB{Df@f@fIxAvThCz`@p@vKNpB_D\\YAyBTiJfAaD^`D_@bFk@~Fq@X@ZCxDe@jJiAdVsCrC]b@zGh@lIDj@^EfAMhBU|H_AnD[xJiA`d@oFl`@wEtUqCrJkATITg@\\m@lAw@b@[p@a@`@KjGWvDQdJa@hMi@bBtEjAnJd@xDRlAbCKdESPnGZnL?L?MG}Ab@EdBQVAfAF`CFd@HD|@o@vA?Nc@p@o@nAPT?Ha@rBQpB_AzCChAe@b@oC?_DOaBR?NDDzCpAtBr@`AnATjAPfCl@tIRxBx@pDh@zCfAhCxAhBp@^bATfARx@`@zDzBND]zAMj@Oz@CXDNx@PvDdBv@f@^l@V|@HzBJjF}@hE_C~Cg@^_AV{@QsAOoDLeBx@eC`AaA\\iB~BEf@Tx@B|AzNe@jCIJ?b@Hn@JvB^hABz@SpAo@x@o@~@mAdAyAxEaF|FqC~F}FfBaBZEh@Nn@l@^b@T]`@a@PUN_@?{@?qAj@mGz@yFf@kCnAwCH}F?yB\\oAvAkAlIqG`@QX]d@{@`@uBZuHb@qBl@y@|@WxAKh@[d@y@\\{CZgAb@k@n@a@d@i@b@kAb@oC^k@j@WjCRz@@~Bg@~@?z@l@j@hAr@rAVb@KRCRALDb@P\\TLf@TZBx@F^ENUpAmCZeA?SLe@|@ABs@B[Rs@Dm@?Mb@?tB?fABb@??l@@tD?xA@tL?jA?kAAkH?cFAuD?m@b@?pBAjB?v@A?c@AcN@yIFaHl@}BTcBGoAw@oDuAuF[{AIaAIuAEcD`@uH`@gDz@mHb@mI?oFQ}EoAaQ@}QKaD]qD]sBuAwEaFuNiDqJgBeCmCmBaCs@iBMoA@yC\\kDb@kBBo@Cw@JcGuAwCuAgBsAyCaCwDcC_DgAaB]}EcAeJsB}Q{DwEWaK^mG\\aC`@kBx@_CzB}CzEoAtAmAr@yCt@}BBaAMqBq@{BqAkAm@wBu@yFe@uEEaSv@iFZsCv@uA~@iDdBqCd@wAAcBOkC}@_CqA}BmBkAoAgAaBoBaEkDgIq@_Bm@_BaBmCkCcFQcAA{@Mo@sC}DgDoEY_@uAjB_C`D{@nA{C`EyEnG{C`EwAlBsAhBIN`ApAx@hAl@x@nCvDx@z@nCvDvBtCxEtGzArBnBlCuAlBiCnDUh@Kb@SNgBPsC^cHz@sQtBkOfBuNhB{C\\iAB]qGEk@WB");
        orderInfo.setPolyline2("qwteFhncjVof@|FIeACe@Bd@HdA@b@f@dHXnEp@hKBl@a@RmFp@yGp@{CZsDf@i@DLhBl@tIpBtZ\\pFFhAzDg@v@Kw@J{Df@GiASyC_@iGmBwX_C}^cEwo@]_FB}@fBiEfBuCfDgF`BeCxI}H`TsQtR{OhCwAdBcBh@mA`@qAAKQSSVcAdAgA~@aBvASd@a@f@DVP@hBi@vAeAxCgFpDgE~BqCfAg@zDuApB{BtDaFt@_ATQr@bAfA`BX`@bCpDvGzIjBdCb@?n@Wb@_@LYFQ?sAm@mC{EyFaP{Pwi@ul@eGuGgKoLqb@ee@iIuI{G}HqV{WiEuFaEiHkQu^uDoJeBsH}@{GsAoPmCy]eH_~@oAcVo@kUiAmRcG{m@_D{]{AkW]{CaBaK_D{UkCkReAsEcBoCuCwBcFkAsDk@sDCkCXiBf@{FhCwMzGaAl@sAf@wBh@oBTaStBgm@rI}S~Ck]nGwz@xO}fAdUsZbEcCB{Kh@oG?yASN}AGuDc@_Fk@i[?wF^{B`C}FLsAkFjAeBl@sFnArFoA`BSNCBnBBrBB`DR`I^tUr@xKMzB_B~Dc@z@S`@VZLLRF`BElC_@xIqAbCi@^c@zBYfEm@bMiBlJuAl_@wHre@cKde@{Itt@}MfS{Ctg@_H`UgC`IkA|VyI`D}@xDs@zC[`FEbCf@rAl@hA~@rBxCj@`BhAzFvBfNhAvG~BlQfAfOt@dV?rNv@zIpChTxClLj@pCnAnKjBjg@pApUrBfXfJplAbBbOpBdIdEbKrLtVlGtLtDrDfGtFrExElMzN|^z`@dU`W`NxNlYj[dNvOvKbLbJ|JtC|D|@fBfBlEnCzEvNdShP|UxE|GhFjJtEfIxBfBtCjA|ATdCBfCUhB]dEcBzAm@vCm@zAK~FYxJ_@rJ[lDZrBn@rCzA|BbA|A\\hD@pCu@bBeAnAwAnCeElCkCdDgArQ}@zACtCHLRTJtCt@~Bv@dBf@lAz@p@|A~@zHrAlGVhCBvGB`Dt@EpCIhAZnDOxI[~BKvAoBRkCHaAvBBlBFbADcAEmBGkBGMJShCa@bB}@fAgAFeEN{J^OAOYm@cBuC}I]gAOKg@_CiA{FsAmMKaB?UU[QdAMD}JeBuECYRyBDyDRcH^uC`@kBt@_CtBqChEqAbBqAx@{C|@mDBuBk@_Bw@{EcCaGm@cFEaPj@cI`@cCh@wGrDkCd@wDGuC}@sBgAiCsBkCaDiGaNoAwCsBeE_DwF[qAGu@Bi@{@uAqEgGwAkB_DfEcBbCaDfE_EnFmFfH");
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(optimizedRoutineTest.setCoordinate(37.7802772, -122.4161605));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.803647, -122.439877));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.759359, -122.442838));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.755209, -122.442838));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.759359, -122.461166));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.732863, -122.446185));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.7860026, -122.403672));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.7946366, -122.4115074));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.800798, -122.412089));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.802705, -122.426801));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.7958379, -122.3937801));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.740053, -122.46066));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.741647, -122.408643));
        coordinates.add(optimizedRoutineTest.setCoordinate(37.7857182, -122.4010508));
        orderInfo.setCoordinates(coordinates);
        List<String> points = new ArrayList<>();
        points.add("Asian Art Museum, CA");
        points.add("3574 Pierce St, CA");
        points.add("191 Caselli Ave, CA");
        points.add("316 Warren Dr, CA");
        points.add("381 Mangels Ave, CA");
        points.add("Contemporary Jewith Museum, CA");
        points.add("San Francisco Cable Car Museum, CA");
        points.add("743 Filbert St, CA");
        points.add("3053 Franklin St, CA");
        points.add("Ferry Building, CA");
        points.add("1 San Pablo Ave, CA");
        points.add("681 Peralta Ave, CA");
        points.add("San Francisco Museum of Modern Art, CA");
        orderInfo.setPoints(points);
        session.save(orderInfo);

        // TODO: 4. Create some completed OrderInfo for testing admin order endpoint
        OrderInfo orderInfo1 = new OrderInfo();
        PickUpAddress pickUpAddress1 = new PickUpAddress();
        pickUpAddress1.setAddress("Anza Vista, CA");
        pickUpAddress1.setCity("San Francisco");
        pickUpAddress1.setCountry("US");
        ShippingAddress shippingAddress1 = new ShippingAddress();
        shippingAddress1.setAddress("Hayes Valley, CA");
        shippingAddress1.setCity("San Francisco");
        shippingAddress1.setCountry("US");
        orderInfo1.setPickUpAddress(pickUpAddress1);
        orderInfo1.setShippingAddress(shippingAddress1);
        List<Coordinate> coordinates1 = new ArrayList<>();
        coordinates1.add(optimizedRoutineTest.setCoordinate(37.7857182, -122.4010508));
        coordinates1.add(optimizedRoutineTest.setCoordinate(37.7808685, -122.4431851));
        coordinates1.add(optimizedRoutineTest.setCoordinate(37.7759073, -122.4245247));
        orderInfo1.setCoordinates(coordinates1);
        List<String> points1 = new ArrayList<>();
        points1.add("San Francisco Museum of Modern Art, CA");
        points1.add("Anza Vista, CA");
        points1.add("Hayes Valley, CA");
        orderInfo1.setPoints(points1);
        orderInfo1.setPolyline1("This should be generated from frontend");
        session.save(orderInfo1);

        session.getTransaction().commit();
        session.close();
    }
}
