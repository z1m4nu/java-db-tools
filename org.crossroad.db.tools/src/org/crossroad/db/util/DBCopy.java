/**
 * 
 */
package org.crossroad.db.util;

import java.io.File;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.crossroad.db.util.config.Configuration;
import org.crossroad.db.util.config.IMemberExport;
import org.crossroad.db.util.config.IMemberImport;
import org.crossroad.db.util.operations.AbstractOperation;
import org.crossroad.db.util.operations.CSVExport;
import org.crossroad.db.util.operations.CSVImport;
import org.crossroad.db.util.operations.CopyOperationFactory;
import org.crossroad.db.util.operations.SQLImport;
import org.crossroad.util.cfg.DirHelper;
import org.crossroad.util.log.AbstractLogger;
import org.crossroad.util.stat.RuntimeStatManager;

/**
 * @author e.soden
 *
 */
public class DBCopy extends AbstractLogger {

	/**
	 * 
	 */
	public DBCopy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.setProperty("logfilename", DirHelper.getInstance().getOutPath("dbcopy.log"));
		DBCopy dbUtil = new DBCopy();
		dbUtil.parseCommand(args);
	}

	protected void parseCommand(String[] args) throws Exception {
		try {
			RuntimeStatManager.getInstance().startOverall();

			CommandLineParser parser = new BasicParser();

			// create the command line Options
			Options options = new Options();

			options.addOption("h", "help", false, "Display current help");

			options.addOption("H", "home-directory", true, "Home directory");

			// Instantiate the helpformatter
			HelpFormatter formatter = new HelpFormatter();

			CommandLine cmdLine = parser.parse(options, args);

			if (cmdLine.hasOption('h')) {
				formatter.printHelp("DBCopy", options);
				System.exit(0);
			} else if (cmdLine.hasOption('H')) {

				Configuration configuration = new Configuration(cmdLine.getOptionValue('H'));
				configuration.loadConfiguration();

				IMemberExport source = configuration.getMember("source");
				IMemberImport target = configuration.getMember("target");

				RuntimeStatManager.getInstance().addCustomInfo("Commit block", String.valueOf(target.getCommitBlock()));
				RuntimeStatManager.getInstance().addCustomInfo("Fetch size", String.valueOf(source.getFetchsize()));

				long rows = CopyOperationFactory.create(source, target).operate();
				RuntimeStatManager.getInstance().addCustomInfo("Rows retrieved", String.valueOf(rows));

			} else {
				throw new RuntimeException("Home directory flags is mandatory");
			}

		} finally {
			RuntimeStatManager.getInstance().stopOverall();

			log.info(RuntimeStatManager.getInstance().displayStat());
		}

	}

}
