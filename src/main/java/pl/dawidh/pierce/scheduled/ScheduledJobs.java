package pl.dawidh.pierce.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.dawidh.pierce.service.ImportFile;

@Component
public class ScheduledJobs {

    private static final Logger log = LoggerFactory.getLogger(ScheduledJobs.class);
    private final String startImportMassage = "Start importing files";
    private final String endImportMassage = "Finish importing files";
    private final ImportFile importFile;

    public ScheduledJobs(ImportFile importFile) {
        this.importFile = importFile;
    }

    @Scheduled(fixedRateString = "${import.file.scheduler.rate}")
    public void todoMethod(){
        log.info(startImportMassage);
        importFile.runFileImport();
        log.info(endImportMassage);
    }
}
