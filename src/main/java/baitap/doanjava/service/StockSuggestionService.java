package baitap.doanjava.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockSuggestionService {
    /**
     * Gợi ý số lượng nhập hàng cho từng sản phẩm dựa trên doanh số bán ra gần đây.
     * 
     * @param salesData    Map<MaSP, doanh số bán ra trong kỳ gần nhất>
     * @param currentStock Map<MaSP, số lượng tồn kho hiện tại>
     * @return Map<MaSP, số lượng nên nhập thêm>
     */
    public Map<String, Integer> suggestStock(Map<String, Integer> salesData, Map<String, Integer> currentStock) {
        Map<String, Integer> suggestion = new HashMap<>();
        for (String maSP : salesData.keySet()) {
            int sold = salesData.getOrDefault(maSP, 0);
            int stock = currentStock.getOrDefault(maSP, 0);
            // Gợi ý nhập thêm nếu tồn kho < 1.5 lần doanh số kỳ gần nhất
            int threshold = (int) Math.ceil(sold * 1.5);
            if (stock < threshold) {
                suggestion.put(maSP, threshold - stock);
            }
        }
        return suggestion;
    }
}
