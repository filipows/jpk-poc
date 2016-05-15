package com.myapp;

import com.myapp.models.AccountState;
import org.jamel.dbf.processor.DbfProcessor;
import org.jamel.dbf.processor.DbfRowMapper;
import org.jamel.dbf.utils.DbfUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class App 
{
    private static final String DB_PATH = "./data/KSO_08.DBF";
    private static final String OUTPUT = "output.xml";

    public static void main( String[] args ) {
        System.out.println( "Start app!" );

        File dbf = new File(DB_PATH);

        String dbfInfo = DbfProcessor.readDbfInfo(dbf);
        System.out.println(dbfInfo);

        List<AccountState> accountStates = DbfProcessor.loadData(dbf, new DbfRowMapper<AccountState>() {
            public AccountState mapRow(Object[] row) {
                String konto = new String(DbfUtils.trimLeftSpaces((byte[]) row[0]));
                Number boSaldo = (Number) row[1];
                Number boWn = (Number) row[2];
                Number boMa = (Number) row[3];

                return new AccountState(konto, boSaldo, boWn, boMa);
            }
        });
        for (AccountState as: accountStates) {
            if (as.boSaldo.floatValue() > 0) {
                System.out.println(as);
                jaxbObjectToXML(as);
                System.exit(0);
            }
        }

    }

    private static void jaxbObjectToXML(AccountState as) {
        try {
            JAXBContext context = JAXBContext.newInstance(AccountState.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(as, new File(OUTPUT));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
