var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "3000",
        "ok": "3000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "103",
        "ok": "103",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2930",
        "ok": "2930",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "787",
        "ok": "787",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "452",
        "ok": "452",
        "ko": "-"
    },
    "percentiles1": {
        "total": "926",
        "ok": "926",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1167",
        "ok": "1167",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1346",
        "ok": "1345",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1490",
        "ok": "1490",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1307,
        "percentage": 44
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1097,
        "percentage": 37
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 596,
        "percentage": 20
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "30.303",
        "ok": "30.303",
        "ko": "-"
    }
},
contents: {
"req_blocking-by101-c1872": {
        type: "REQUEST",
        name: "blocking_by101",
path: "blocking_by101",
pathFormatted: "req_blocking-by101-c1872",
stats: {
    "name": "blocking_by101",
    "numberOfRequests": {
        "total": "3000",
        "ok": "3000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "103",
        "ok": "103",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2930",
        "ok": "2930",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "787",
        "ok": "787",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "452",
        "ok": "452",
        "ko": "-"
    },
    "percentiles1": {
        "total": "926",
        "ok": "926",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1167",
        "ok": "1167",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1346",
        "ok": "1346",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1490",
        "ok": "1490",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1307,
        "percentage": 44
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1097,
        "percentage": 37
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 596,
        "percentage": 20
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "30.303",
        "ok": "30.303",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
