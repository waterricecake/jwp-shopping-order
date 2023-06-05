package cart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberCouponDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberCouponDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean checkByMemberIdCouponId(final long memberId, final long couponId) {
        final String sql = "SELECT EXISTS (SELECT * FROM member_coupon WHERE member_id = ? and coupon_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, memberId, couponId);
    }

    public void create(final long memberId, final long couponId) {
        final String sql = "INSERT INTO member_coupon(member_id,coupon_id) values(?,?)";
        jdbcTemplate.update(sql, memberId, couponId);
    }

    public List<Long> findByMemberId(long memberId) {
        final String sql = "SELECT coupon_id FROM member_coupon WHERE member_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("coupon_id"), memberId);
    }

    public void delete(long memberId, Long couponId) {
        final String sql = "DELETE FROM member_coupon WHERE member_id = ? AND coupon_id =?";
        jdbcTemplate.update(sql, memberId, couponId);
    }
}
