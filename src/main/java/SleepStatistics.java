import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Dream
 *
 * 快捷指令统计睡眠总时长。
 *
 * 快捷指令中健康查找所有睡眠分析，持续时间即可
 *
 * https://www.icloud.com/shortcuts/1ede314ff2424e62967cfd8b6665ce44
 */
@WebServlet("/cal")
public class SleepStatistics extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String time = req.getParameter("time");
        if (time == null) {
            out.print("参数错误");
            return;
        }
        String[] times = time.split("\r\n");
        //单位：分
        int local = 0;
        for (String once : times) {
            String[] temp = once.substring(0, once.length() - 3).split(":");
            if (temp.length == 1) {
                local += Integer.parseInt(temp[0]);
            } else {
                local += Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
            }
        }
        int hour = local / 60;
        int min = local % 60;
        out.print(hour + "小时" + min + "分钟");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
