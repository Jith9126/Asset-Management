package util;

public class Constants {
	public static String loginStatement = "SELECT * FROM User WHERE UserID = ?";
	public static String getDeatilaStmt = "SELECT Employee.EmpName AS Name, Employee.EmpID AS ID, Employee.MailID AS Email, 'Employee' AS Role FROM Employee JOIN User ON Employee.EmpID = User.UserID HAVING EmpID = ?";
	public static String getDetailsOfAdmin = "SELECT Vendor.VendorID AS `VendorID`, Vendor.VendorName AS `Name`, User.UserType AS `Role`, Vendor.MailID AS `Email` FROM Vendor JOIN User ON Vendor.VendorID = User.UserID where `VendorID` = ?";
	public static String getAsset = "SELECT  U.UserID, E.EmpName, A.AssetName, T.TypeName, A.AssetDetails FROM User U LEFT JOIN Employee E ON U.UserID = E.EmpID AND U.UserType = 'Employee' LEFT JOIN Asset A ON U.UserID = A.EmployeeID LEFT JOIN Type T ON A.AssetTypeID = T.TypeID WHERE U.UserType = 'Employee' HAVING `UserID` like ?";
	public static String getFromSessionId = "SELECT userID FROM session WHERE `sessionID` like ?";
	public static String getAssetFromUserID= "SELECT Asset.AssetName, Asset.AssetDetails, Asset.AssetID, Type.TypeName FROM Employee LEFT JOIN Asset ON Employee.EmpID = Asset.EmployeeID LEFT JOIN Type ON Asset.AssetTypeID = Type.TypeID WHERE Employee.EmpID LIKE ?";
	public static String selectAdminWhoHasLowestWorks = "SELECT VendorID FROM Report WHERE Status = 'Pending' GROUP BY VendorID ORDER BY COUNT(*) ASC LIMIT 1";
	
	
	
	public static String insertIntoSession = "INSERT INTO `AssetDummy`.`session` (`sessionID`, `userID`, `createdAt`, `Expires`) VALUES (?, ?, ?, ?);";
	
	
	
	
	public static String insertReport= "INSERT INTO Report (VendorID, EmpID, AssetID, ReportDate, Notes, Status, ReportType, Title) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static String insertReportForNewRequest= "INSERT INTO Report (VendorID, EmpID, RequestableAssetID, ReportDate, Notes, Status, ReportType, Title) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


	
}
