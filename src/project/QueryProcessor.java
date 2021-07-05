package project;

public class QueryProcessor extends Filtre {

	public QueryProcessor(Pipe _dataINPipe, Pipe _dataOUTPipe) {
		super();
		this._dataINPipe = _dataINPipe;
		this._dataOUTPipe = _dataOUTPipe;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}

	@Override
	synchronized void execute() {
		// TODO Auto-generated method stub
		String requete;
		String[] rqst;
		String outStr;
		do {
			requete = _dataINPipe.dataOUT();
			rqst = requete.split("@");
			outStr = "{";
			for (String str : rqst) {
				outStr += "\"" + str + "\",";
			}
			outStr += "}";
			_dataOUTPipe.dataIN(outStr);
		} while (rqst[0] != "0");

	}
}
