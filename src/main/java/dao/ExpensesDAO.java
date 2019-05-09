package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Expenses;

/**
 * 社員データを扱うDAO
 */
public class ExpensesDAO {
	/**
	 * クエリ文字列
	 */
	private static final String SELECT_ALL_QUERY = "SELECT * "
			+ "FROM EXPENSES";
//	private static final String SELECT_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE EMP.ID = ?";
//	private static final String INSERT_QUERY = "INSERT INTO "
//							+"EMPLOYEE(EMPID, NAME, AGE, GENDER, PHOTOID, ZIP, PREF, ADDRESS, POSTID, ENTDATE, RETDATE) "
//							+"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//	private static final String UPDATE_QUERY = "UPDATE EMPLOYEE "
//							+"SET EMPID=?,NAME=?,AGE=?,GENDER=?,PHOTOID=?,ZIP=?,PREF=?,"
//							+"ADDRESS=?,POSTID=?,ENTDATE=?,RETDATE=? WHERE ID = ?";
//	private static final String DELETE_QUERY = "DELETE FROM EMPLOYEE WHERE ID = ?";

	/**
	 * ID指定の検索を実施する。
	 *
	 * @param id 検索対象のID
	 * @return 検索できた場合は検索結果データを収めたPostインスタンス。検索に失敗した場合はnullが返る。
	 */


	/**
	 * パラメータ指定の検索を実施する。
	 * 有効なパラメータ指定が1つも存在しない場合は全件検索になる。
	 *
	 * @param param 検索用のパラメータを収めたオブジェクト。
	 * @return 検索結果を収めたList。検索結果が存在しない場合は長さ0のリストが返る。
	 */
	public List<Expenses> findByParam(Param param) {
		List<Expenses> result = new ArrayList<>();

		Connection connection = ConnectionProvider.getConnection();
		if (connection == null) {
			return result;
		}

		String queryString = SELECT_ALL_QUERY;
		try (PreparedStatement statement = connection.prepareStatement(queryString)) {
			//param.setParameter(statement);

			ResultSet rs = statement.executeQuery();

			System.out.println("AA");

			while (rs.next()) {
				result.add(processRow(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(connection);
		}

		return result;
	}

	/**
	 * 検索結果からオブジェクトを復元する。
	 *
	 * @param rs 検索結果が収められているResultSet。rs.next()がtrueであることが前提。
	 * @return 検索結果を収めたオブジェクト
	 * @throws SQLException 検索結果取得中に何らかの問題が発生した場合に送出される。
	 */
	private Expenses processRow(ResultSet rs) throws SQLException {
		Expenses result = new Expenses();

		// Expenses本体の再現
		result.setId(rs.getInt("ID"));
		result.setTitle(rs.getString("TITLE"));
//		result.setEmpId(rs.getString("EMPID"));
//		result.setName(rs.getString("NAME"));
//		result.setAge(rs.getInt("AGE"));
//		result.setGenderByInt(rs.getInt("GENDER"));
//		result.setPhotoId(rs.getInt("PHOTOID"));	// Photoオブジェクトに関しては必要になるまで生成しない
//		result.setZip(rs.getString("ZIP"));
//		result.setPref(rs.getString("PREF"));
//		result.setAddress(rs.getString("ADDRESS"));
//		Date entDate = rs.getDate("ENTDATE");
//		if (entDate != null) {
//			result.setEnterDate(entDate.toString());
//		}
//		Date retDate = rs.getDate("RETDATE");
//		if (retDate != null) {
//			result.setRetireDate(retDate.toString());
//		}

//		// 入れ子のオブジェクトの再現
//		Post post = new Post();
//		post.setId(rs.getInt("POSTID"));
//		post.setName(rs.getString("POST_NAME"));
//		result.setPost(post);

		return result;
	}
}
